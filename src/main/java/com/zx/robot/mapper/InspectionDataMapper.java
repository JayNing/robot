package com.zx.robot.mapper;

import com.zx.robot.entity.InspectionData;

import java.util.List;

public interface InspectionDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionData record);

    int insertSelective(InspectionData record);

    InspectionData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionData record);

    int updateByPrimaryKey(InspectionData record);

    InspectionData selectNewLastUser();

    List<InspectionData> selectByCreateTime(String createTime);
}