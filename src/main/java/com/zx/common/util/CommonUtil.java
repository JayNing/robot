package com.zx.common.util;

import java.util.List;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:21:08
 * //TODO 通用工具类
 */
public class CommonUtil {

    public static boolean isListEmpty(@SuppressWarnings("rawtypes") List list) {
        return (list == null) || (list.size() == 0);
    }

    /**
     * 判断时间是否过期
     * @param startTime 计时时间
     * @param timePeriodBySeconds 有效期(秒) 
     * @return
     */
    public static boolean timeExpired(Long startTime, int timePeriodBySeconds) {
        if ((startTime + (timePeriodBySeconds * 1000)) < System.currentTimeMillis()) {
            return true;
        }
        return false;
    }
}
