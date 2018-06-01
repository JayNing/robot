package com.zx.robot.server.session;


import com.zx.robot.server.TransportMessage;

import java.io.IOException;

/**
 * session接口
 *
 * @author ning
 */
public interface ISession {

    /**
     * 写数据
     * @param msg 要写的数据
     */
    void write(TransportMessage msg);

    /**
     * 获取唯一ID
     * @return ID
     */
    String getId();

    /**
     * 关闭
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * 是否关闭
     * @return 关闭状态
     */
    boolean isOpen();
}
