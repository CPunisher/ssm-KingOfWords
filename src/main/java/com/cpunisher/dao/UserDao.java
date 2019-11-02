package com.cpunisher.dao;

import com.cpunisher.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    void register(@Param("openId") String openId, @Param("password") String password, @Param("nickname") String nickname,
                  @Param("iconUrl") String iconUrl);

    User getUserById(@Param("id") int id);

    User getUserByOpenId(@Param("openId") String openId);

    User validateUser(@Param("openId") String openId, @Param("password") String password);
}
