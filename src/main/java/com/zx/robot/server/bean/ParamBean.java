package com.zx.robot.server.bean;

import java.io.Serializable;

public class ParamBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String key;
    private String value;
    
    public ParamBean() {
        // TODO Auto-generated constructor stub
    }
    
    public ParamBean(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "{\"key\":\"" + key + "\",\"value\":\"" + value + "\"}  ";
    }
}
