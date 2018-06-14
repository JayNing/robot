package com.zx.common.schedule;

import com.zx.common.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserInfoUpdateTimer {

    private static Logger logger = LoggerFactory.getLogger(UserInfoUpdateTimer.class);

    @Autowired
    private SysUserService sysUserService;

//    @Scheduled(fixedDelay = 60 * 1000 * 3, initialDelay = 1000 * 10)
    @Scheduled(cron = "0 0 0 * * ? ")
    public void userInfoUpdateTimer() {
        logger.info("userInfoUpdateTimer is called...... " + LocalDateTime.now());
        sysUserService.updateUserInfoTimer();
    }


}