package com.zx.common.service;

import com.zx.common.entity.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> selectAllUser();

    void updateUserInfoTimer();
}