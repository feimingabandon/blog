package com.zou.controller;

import com.alibaba.fastjson.JSONObject;
import com.zou.tool.JsonData;
import com.zou.tool.State;
import com.zou.pojo.User;
import com.zou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


// 控制层进行传输
@RestController//使用的效果是将方法返回的对象直接在浏览器上展示成json格式.
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    JSONObject jsonObject = new JSONObject();

    // 添加用户
    @GetMapping("/addUser/{name}/{pwd}")
    public JSONObject addUser(@PathVariable String name,@PathVariable String pwd) {

        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        // 设置注册时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setRegisterTime(dateFormat.format(date));

        Integer integer = userService.addUser(user);

        if (integer==null)
            return JsonData.json(jsonObject,"啊偶，好像注册失败了",null, State.fail);
        return JsonData.json(jsonObject,"ok!注册成功！",integer, State.success);

    }

    // 通过用户名查询用户
    @GetMapping("/selectUserName/{name}")
    public int selectUserName(@PathVariable String name) {


        return userService.selectUserName(name);
    }

    // 验证密码、返回用户信息(登陆)
    @GetMapping("/selectUserPwd/{name}/{pwd}")
    public JSONObject selectUserPwd(@PathVariable("name") String name,@PathVariable("pwd") String pwd) {

        System.out.println("接收到的数据："+name+" pwd："+pwd);

        // 定义需要的参数
        String message = "";   // 错误或成功详细信息
        Object data = null ;       // 最终要传递的数据
        int state = State.fail;            // 成功为1失败为0

        // 先判断用户名是否存在
        Integer integer = userService.selectUserName(name);
        // 如果没查询到会返回-1
        if(integer!=-1){

            // 再通过id查询用户所有信息
            User user = userService.selectUser(integer);

            // 再判断密码是否正确
            if(user.getPwd().equals(pwd)){
                message="登陆成功";
                data=user;
                state=State.success;

            }else  message="密码出错";

        }else message="用户名出错";

        return JsonData.json(jsonObject,message,data, state);
    }

}
