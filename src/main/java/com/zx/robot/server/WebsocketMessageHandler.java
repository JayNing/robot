package com.zx.robot.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.CommonUtil;
import com.zx.robot.server.contants.ServerContant;
import com.zx.robot.server.session.ISession;
import com.zx.robot.server.session.ServerSessionManager;
import com.zx.robot.server.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 接收消息处理
 *
 * @author ning
 */
@Component
public class WebsocketMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(WebsocketMessageHandler.class);

    @Autowired
    private ServerSessionManager sessionManager;

    private static final String REQUEST_METHOD_NAME = "requestMethod";

    public void handleMsg(ISession session, String body) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        boolean requestMethod = jsonObject.has(REQUEST_METHOD_NAME);
        if (requestMethod) {
            String method = jsonObject.get(REQUEST_METHOD_NAME).getAsString();
            if (ServerContant.PONG_METHOD.equals(method)) {
                sessionManager.keepAlive(session);
            } else {
                // 由dispatcher处理
//                ReturnMsg respMsg = handReq(method, session);
                ReturnMsg respMsg = new ReturnMsg();
                respMsg.setErrorCode(0);
                respMsg.setData("我是websocket，我被调用了，时间 ： " + LocalDateTime.now());
                session.write(ProtocolResponseBuilder.responseMsg(respMsg, method));
            }
        } else {
            session.close();
        }
    }

    public void broadcastInfo(String robotId, Object msg) {
        List<SessionContext> buildSessions = sessionManager.buildSessions(robotId);
        if (!CommonUtil.isListEmpty(buildSessions)) {
            for (SessionContext sessionContext : buildSessions) {
                ReturnMsg respMsg = new ReturnMsg();
                respMsg.setData(msg);
                respMsg.setErrorCode(0);
                sessionContext.write(ProtocolResponseBuilder.responseMsg(respMsg, "broadcastInfo"));
            }
        }
    }
}
