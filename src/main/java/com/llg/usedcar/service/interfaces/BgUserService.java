package com.llg.usedcar.service.interfaces;

import com.llg.usedcar.entity.BgUser;

public interface BgUserService {

    BgUser selectUserByUsername(String username);
    BgUser selectUserByUserId(Long userId);

    int updatePassword(BgUser bgUser);
}
