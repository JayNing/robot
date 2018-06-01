package com.zx.robot.server;

import com.zx.common.entity.ReturnMsg;
import com.zx.robot.server.contants.ServerContant;

/**
 * 协议回复
 *
 * @author qingzhou
 *         2018-01-15 18:08
 */
public class ProtocolResponseBuilder {

    private static final String DIRECTION_SERVER_TO_CLIENT = "sToC";
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static TransportMessage<Void> buildPing() {
        return new TransportMessage(DIRECTION_SERVER_TO_CLIENT, ServerContant.PING_METHOD,new ReturnMsg().setErrorCode(0));
    }

    public static TransportMessage<ReturnMsg> responseMsg(ReturnMsg data,String method) {
        return new TransportMessage(DIRECTION_SERVER_TO_CLIENT, method, method, data);
    }
}
