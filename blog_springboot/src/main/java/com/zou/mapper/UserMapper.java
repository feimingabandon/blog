package com.zou.mapper;


import com.zou.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     // 告诉 MyBatis 这是个Mapper，然后就会匹配xml文件去实现里面的方法
@Repository // 对象交给spring管理。
public interface UserMapper {
    // 通过用户名查询用户
    Integer selectUserName(String name);

    // 通过id查询用户信息
    User selectUser(int id);

    // 添加用户
    Integer addUser(User user);
}
