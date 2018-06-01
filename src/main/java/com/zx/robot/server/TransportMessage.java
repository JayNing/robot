package com.zx.robot.server;

import java.io.Serializable;

/**
 * 协议传输对象
 *
 * @author qingzhou
 *         2018-01-15 17:35
 */
public class TransportMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String direction;
    private String requestMethod;
    private String responseType;
    private T data;
    private long time;

    public TransportMessage(String direction, String responseType) {
        this.direction = direction;
        this.responseType = responseType;
    }

    public TransportMessage(String direction, String responseType, T data) {
        this.direction = direction;
        this.responseType = responseType;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public TransportMessage(String direction, String requestMethod, String responseMethod, T data) {
        this.direction = direction;
        this.requestMethod = requestMethod;
        this.responseType = responseMethod;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public TransportMessage() {
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TransportMessage{" +
                "direction='" + direction + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", responseType='" + responseType + '\'' +
                ", data=" + data +
                ", time=" + time +
                '}';
    }
}
