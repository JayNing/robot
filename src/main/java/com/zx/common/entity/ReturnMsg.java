package com.zx.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:36:09
 * //TODO 接口返回封装实体类
 */
public class ReturnMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 验证请求是否成功， (成功：0)
     */
    private int errorCode = -90;

    /**
     * 如果出错，出现错误的信息
     */
    private List<String> errorMsg = new ArrayList<String>();

    private Map<String, List<String>> filedErr = new HashMap<>();

    /**
     * 返回的结果集
     */
    private Object data;

    public boolean isValid() {
        return this.errorMsg.size() == 0 && this.filedErr.size() == 0;
    }

    public void addFiledError(String field, String error) {
        if (field != null && error != null) {
            List<String> errs = filedErr.get(field);
            if (errs == null) {
                errs = new ArrayList<>();
            }
            errs.add(error);
            filedErr.put(field, errs);
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ReturnMsg setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public void addErrorMsg(String msg) {
        this.errorMsg.add(msg);
    }

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public ReturnMsg setData(Object data) {
        this.data = data;
        return this;
    }

    public Map<String, List<String>> getFiledErr() {
        return filedErr;
    }

    public void setFiledErr(Map<String, List<String>> filedErr) {
        this.filedErr = filedErr;
    }

    @Override
    public String toString() {
        return "ReturnMsg [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", filedErr=" + filedErr + ", data=" + data + "]";
    }

}
