package com.zx.robot.server.session;

import com.zx.robot.server.TransportMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 会话上下文
 *
 * @author ning
 */
public class SessionContext implements ISession {

    private Map<String, String> params;
    private ISession session;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public SessionContext(ISession session) {
        this.session = session;
        this.params = new HashMap();
    }

    @Override
    public void write(TransportMessage msg) {
        session.write(msg);
    }

    @Override
    public String getId() {
        return session.getId();
    }

    @Override
    public void close() throws IOException {
        session.close();
    }

    @Override
    public boolean isOpen() {
        return session.isOpen();
    }
}
