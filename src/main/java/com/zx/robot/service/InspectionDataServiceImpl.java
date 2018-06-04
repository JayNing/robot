package com.zx.robot.service;

import com.zx.robot.entity.InspectionData;
import com.zx.robot.mapper.InspectionDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机器人service层实现类
 *
 * @author ning
 * @create 2018-06-04 10:03
 **/
@Service("inspectionDataService")
public class InspectionDataServiceImpl implements InspectionDataService{

    @Autowired
    private InspectionDataMapper inspectionDataMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void insert(InspectionData inspectionData) {
        inspectionDataMapper.insert(inspectionData);
    }
}