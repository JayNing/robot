package com.zx.robot.schedule;

import com.zx.robot.entity.InspectionData;
import com.zx.robot.mapper.InspectionDataMapper;
import com.zx.robot.server.WebsocketMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时检测机器人数据有没有更新定时任务
 * @author ning
 * @create 2018-06-04 9:49
 **/
@Component
public class RefreshRobotInfoTimer {

    private static Logger logger = LoggerFactory.getLogger(RefreshRobotInfoTimer.class);
    private static Integer newLastId = 0;

    @Autowired
    private InspectionDataMapper inspectionDataMapper;
    @Autowired
    private WebsocketMessageHandler websocketMessageHandler;


    @Scheduled(fixedDelay = 1000 * 1, initialDelay = 1000 * 5)
    public void refreshRobotInfoTimer() {

        logger.info("refreshRobotInfoTimer is called...... " + LocalDateTime.now());

        InspectionData inspectionData = inspectionDataMapper.selectNewLastUser();

        if (inspectionData != null){
            if (newLastId < inspectionData.getId()){
                newLastId = inspectionData.getId();
                websocketMessageHandler.broadcastInfo("1",inspectionData);
            }
        }else{
            logger.info("inspectionData is null ! Time is : " + LocalDateTime.now());
        }
    }


}