package com.zx.robot.server.contants;

import com.zx.robot.server.ProtocolResponseBuilder;
import com.zx.robot.server.TransportMessage;

public class ServerContant {

    public static final Integer PING_INTERVAL_SECONDS = 5;
    public static final Integer DISCONNECT_TIME_SECONDS = 15;

    public static final TransportMessage<Void> PING_MESSAGE = ProtocolResponseBuilder.buildPing();
    public static final String PING_METHOD = "ping";
    public static final String PONG_METHOD = "pong";
}