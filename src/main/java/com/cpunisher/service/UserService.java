package com.cpunisher.service;

import com.cpunisher.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    void register(String openId, String password, String nickname, String iconUrl);

    User getUserById(int id);

    User getUserByOpenId(String openId);

    User validateUser(String openId, String password);
}
