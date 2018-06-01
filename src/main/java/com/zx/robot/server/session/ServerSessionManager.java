package com.zx.robot.server.session;

import com.zx.common.util.StringUtil;
import com.zx.robot.server.contants.ServerContant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 会话管理器
 *
 * @author ning
 */
@Component
public class ServerSessionManager {

    private static Logger logger = LoggerFactory.getLogger(ServerSessionManager.class);
    
    /**
     * 从sessionId到robotId的映射
     */
    private ConcurrentHashMap<String, String> sessionToRobotCache = new ConcurrentHashMap<>();
    /**
     * 从robotId到session列表的映射
     */
    private ConcurrentHashMap<String, ConcurrentHashMap<String, SessionContext>>
            sessionCache = new ConcurrentHashMap<>();
    /**
     * 从sessionId到最后pong时间
     */
    private ConcurrentHashMap<String, Long> sessionAliveMap = new ConcurrentHashMap<>();

    private ScheduledThreadPoolExecutor deadChecker = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("death-session-check");
            return t;
        }
    });

    public ServerSessionManager() {
        deadChecker.scheduleAtFixedRate(/**
         * @author ning
         * //TODO 开启轮循定时任务
         */
        new Runnable() {
            @Override
            public void run() {
                try {
                    Set<String> sessionIds = sessionAliveMap.keySet();
                    if (sessionIds != null) {
                        long now = System.currentTimeMillis();
                        for (String sessionId : sessionIds) {
                            try {
                                Long lastPongTime = sessionAliveMap.get(sessionId);
                                if (lastPongTime != null) {
                                    if (lastPongTime + ServerContant.DISCONNECT_TIME_SECONDS * 1000 < now) {
                                        //disconnect 超时断开链接
                                        removeSession(sessionId);
                                        sessionAliveMap.remove(sessionId);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                //TODO
                                logger.info("定时检查链接是否断开,遍历sessionIds异常 " + e.getMessage());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //TODO
                    logger.info("定时检查链接是否断开,定时任务出现异常 " + e.getMessage());
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }


    public List<SessionContext> buildSessions(String robotId) {
        ConcurrentHashMap<String, SessionContext> buildSessions = sessionCache.get(robotId);
        if(buildSessions == null){
            return new ArrayList<>();
        }
        return new ArrayList<>(buildSessions.values());
    }

    public void add(String robotId, ISession session) {
        String sessionKey = session.getId();
        sessionToRobotCache.put(sessionKey, robotId);

        SessionContext sessionContext = new SessionContext(session);

        ConcurrentHashMap<String, SessionContext> buildSessions = sessionCache.get(robotId);
        if (buildSessions == null) {
            buildSessions = new ConcurrentHashMap<>();
            buildSessions.put(sessionKey, sessionContext);
            // putIfAbsent 方法是有返回值的，并且返回值很重要
            ConcurrentHashMap<String, SessionContext> preBuildSessions = sessionCache.putIfAbsent(robotId, buildSessions);
            if (preBuildSessions != null) {
                preBuildSessions.put(sessionKey, sessionContext);
            }
        } else {
            buildSessions.put(sessionKey, sessionContext);
        }
        sessionAliveMap.put(sessionKey, System.currentTimeMillis());
    }

    public void removeSession(ISession session) {
        String sessionKey = session.getId();
        removeSession(sessionKey);
    }

    private void removeSession(String sessionKey) {
        String robotId = sessionToRobotCache.get(sessionKey);
        if (StringUtil.notEmpty(robotId)) {
            ConcurrentHashMap<String, SessionContext> buildSessions = sessionCache.get(robotId);
            if (buildSessions != null) {
                SessionContext sessionContext = buildSessions.get(sessionKey);
                if (sessionContext != null) {
                    try {
                        sessionContext.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                buildSessions.remove(sessionKey);
            }
        }
        sessionToRobotCache.remove(sessionKey);
    }

    public void keepAlive(ISession session) {
        sessionAliveMap.put(session.getId(), System.currentTimeMillis());
    }
    
}
