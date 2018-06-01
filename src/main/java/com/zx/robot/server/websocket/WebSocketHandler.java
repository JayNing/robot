package com.zx.robot.server.websocket;

import com.zx.robot.server.WebsocketMessageHandler;
import com.zx.robot.server.contants.ServerContant;
import com.zx.robot.server.session.ServerSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


/**
 * @author ning
 */
@ServerEndpoint(value = "/websocket/{robotId}", configurator = SpringConfigurator.class)
public class WebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    private WebsocketMessageHandler websocketMessageHandler;

    @Autowired
    private ServerSessionManager sessionManager;

    private WebsocketSession session;
    private ScheduledThreadPoolExecutor pingPool = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("websocket-ping-check");
            return t;
        }
    });

    @OnOpen
    public void onOpen(@PathParam(value = "robotId") String robotId, Session wsSession, EndpointConfig ec) {
        this.session = new WebsocketSession(wsSession);
        sessionManager.add(robotId, session);
        logger.info("有新连接加入！" + robotId);
        logger.info("sessionManager size : " + sessionManager.buildSessions(robotId).size());
        addPingTask();
    }

    private void addPingTask() {
        pingPool.schedule(new Runnable() {
            @Override
            public void run() {
                if (session.isOpen()) {
                    session.write(ServerContant.PING_MESSAGE);
                    addPingTask();
                }
            }
        }, ServerContant.PING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    @OnClose
    public void onClose() {
        logger.info("有一连接关闭！" + session.getId());
        //将session清除
        synchronized(session){
            sessionManager.removeSession(session);
        }
    }

    @OnMessage
    public void onMessage(String message) throws Exception {
        logger.info("来自客户端的消息:" + message);
        websocketMessageHandler.handleMsg(session, message);
    }

    @OnError
    public void onError(Throwable error) {
        logger.info("onError 发生错误" + session.getId());
        logger.info("error ： " + error);
        //将session清除
        sessionManager.removeSession(this.session);

    }

}
