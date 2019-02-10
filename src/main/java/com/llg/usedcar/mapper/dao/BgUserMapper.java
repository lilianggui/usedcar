package com.llg.usedcar.mapper.dao;

import com.llg.usedcar.entity.BgUser;
import org.apache.ibatis.annotations.Param;

public interface BgUserMapper {
    BgUser selectUserByUsername(@Param("username") String username);

    BgUser selectUserByUserId(@Param("userId") Long userId);

    int updatePassword(BgUser bgUser);
}
