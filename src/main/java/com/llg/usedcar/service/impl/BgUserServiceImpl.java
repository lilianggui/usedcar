package com.llg.usedcar.service.impl;

import com.llg.usedcar.entity.BgUser;
import com.llg.usedcar.mapper.dao.BgUserMapper;
import com.llg.usedcar.service.interfaces.BgUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BgUserServiceImpl implements BgUserService{

    @Resource
    private BgUserMapper bgUserMapper;


    @Override
    public BgUser selectUserByUsername(String username) {
        return bgUserMapper.selectUserByUsername(username);
    }

    @Override
    public BgUser selectUserByUserId(Long userId) {
        return bgUserMapper.selectUserByUserId(userId);
    }

    @Override
    public int updatePassword(BgUser bgUser) {
        return bgUserMapper.updatePassword(bgUser);
    }
}
