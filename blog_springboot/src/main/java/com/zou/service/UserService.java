package com.zou.service;

import com.zou.pojo.User;

public interface UserService {

    // 通过用户名查询用户
    Integer selectUserName(String name);

    // 通过id查询用户信息
    User selectUser(int id);

    // 添加用户
    Integer addUser(User user);
}
