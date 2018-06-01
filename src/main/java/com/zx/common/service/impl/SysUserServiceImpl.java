package com.zx.common.service.impl;

import com.zx.common.entity.SysUser;
import com.zx.common.mapper.SysUserMapper;
import com.zx.common.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectAllUser() {
        return sysUserMapper.selectAllUser();
    }
}