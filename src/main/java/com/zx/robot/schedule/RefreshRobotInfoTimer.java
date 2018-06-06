package com.zx.robot.schedule;

import com.zx.common.util.CommonUtil;
import com.zx.common.util.DateUtil;
import com.zx.robot.dto.InspectionDataDTO;
import com.zx.robot.entity.InspectionData;
import com.zx.robot.mapper.InspectionDataMapper;
import com.zx.robot.server.WebsocketMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时检测机器人数据有没有更新定时任务
 * @author ning
 * @create 2018-06-04 9:49
 **/
@Component
public class RefreshRobotInfoTimer {

    private static Logger logger = LoggerFactory.getLogger(RefreshRobotInfoTimer.class);
    private static Integer newLastId = 0;
    public static Long patrolTime = 0L;

    @Autowired
    private InspectionDataMapper inspectionDataMapper;
    @Autowired
    private WebsocketMessageHandler websocketMessageHandler;


    @Scheduled(fixedDelay = 1000 * 1, initialDelay = 1000 * 5)
    public void refreshRobotInfoTimer() {

        logger.info("refreshRobotInfoTimer is called...... " + LocalDateTime.now());
        InspectionDataDTO inspectionDataDTO = null;

        logger.info("patrolTime : " + patrolTime);
        if (patrolTime > 0){
            List<InspectionData> inspectionDataList = inspectionDataMapper.selectByCreateTime(DateUtil.formatDate(new Date(patrolTime),null));
            logger.info("inspectionDataList.size() : " + inspectionDataList.size());


            if (!CommonUtil.isListEmpty(inspectionDataList)){
                new InspectionDataDTO();
                InspectionData inspectionData = inspectionDataList.get(0);
                List<Double> totalT = new ArrayList<>();
                List<Double> totalH = new ArrayList<>();
                //计算最高最低温湿度
                inspectionDataList.forEach(n -> {
                    totalT.add(n.getHight());
                    totalT.add(n.getMiddlet());
                    totalT.add(n.getLowt());

                    totalH.add(n.getHighh());
                    totalH.add(n.getMiddleh());
                    totalH.add(n.getLowh());
                });

                Double maxT = totalT.stream().mapToDouble((x) -> x).summaryStatistics().getMax();
                Double minT = totalT.stream().mapToDouble((x) -> x).summaryStatistics().getMin();
                Double maxH = totalH.stream().mapToDouble((x) -> x).summaryStatistics().getMax();
                Double minH = totalH.stream().mapToDouble((x) -> x).summaryStatistics().getMin();

                inspectionDataDTO = new InspectionDataDTO(maxT,minT,maxH,minH,inspectionData);
            }
            if (inspectionDataDTO != null){
                logger.info("inspectionDataDTO : " + inspectionDataDTO);
                InspectionData inspectionData = inspectionDataDTO.getInspectionData();
                if (newLastId < inspectionData.getId()){
                    newLastId = inspectionData.getId();
                    websocketMessageHandler.broadcastInfo("1",inspectionDataDTO);
                }
            }else{
                logger.info("inspectionDataDTO is null ! Time is : " + LocalDateTime.now());
            }
        }else {
            logger.info("inspectionData patrolTime is : null");
        }
    }


}