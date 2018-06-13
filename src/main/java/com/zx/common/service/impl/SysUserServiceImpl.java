package com.zx.common.service.impl;

import com.zx.common.entity.SysUser;
import com.zx.common.mapper.SysUserMapper;
import com.zx.common.service.SysUserService;
import com.zx.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectAllUser() {
        return sysUserMapper.selectAllUser();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateUserInfoTimer() {
        //根据上一天的日期将sys_user重命名为sys_user_上一天日期

        String beforeDateStr = DateUtil.getBeforeDateString();
        String rename = "sys_user_" + beforeDateStr + UUID.randomUUID().toString().substring(0,16);
        sysUserMapper.renameTable(rename.replace("-",""));
        //新建sys_user表
        sysUserMapper.createTable();
    }
}