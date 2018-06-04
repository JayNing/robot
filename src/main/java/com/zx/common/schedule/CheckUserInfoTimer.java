package com.zx.common.schedule;

import com.zx.common.entity.SysUser;
import com.zx.common.service.SysUserService;
import com.zx.robot.server.WebsocketMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class CheckUserInfoTimer {

    private static Logger logger = LoggerFactory.getLogger(CheckUserInfoTimer.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WebsocketMessageHandler websocketMessageHandler;


//    @Scheduled(fixedDelay = 1000 * 30, initialDelay = 1000 * 5)
    public void checkUserInfo() {

        logger.info("CheckUserInfoTimer is called...... " + LocalDateTime.now());

        List<SysUser> sysUserList = sysUserService.selectAllUser();

        logger.info("sysUserList : " + sysUserList);

        websocketMessageHandler.broadcastInfo("1","CheckUserInfoTimer is called...... " + LocalDateTime.now());
    }


}