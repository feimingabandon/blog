package com.zou.service.lmpl;

import com.zou.mapper.UserMapper;
import com.zou.pojo.User;
import com.zou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    // 通过用户名查询用户
    @Override
    public Integer selectUserName(String name) {
        Integer integer = userMapper.selectUserName(name);
        if (integer==null) integer=-1;
        return integer;
    }

    //// 通过id查询用户信息User
    @Override
    public User selectUser(int id) {

        return userMapper.selectUser(id);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }
}
