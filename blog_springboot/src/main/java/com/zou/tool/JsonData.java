package com.zou.tool;


import com.alibaba.fastjson.JSONObject;



// 用来返回json数据
public class JsonData {

    // 返回的json数据只包含两块信息：message 和 data，
    // message 存储是否成功或者失败或者其他原因
    // data 存储需要返回的数据

//    private Object message;         // 返回请求信息，成功还是失败或者其他原因
//    private Object data;            // 返回数据
//    private int state;          // 成功还是失败

//    private JSONObject jsonObject;

    public JsonData(){}


    // 返回json数据
    public static JSONObject json(JSONObject jsonObject,Object message,Object data,int state){

         jsonObject.put("data",data);
         jsonObject.put("message",message);
         jsonObject.put("state",state);

         return jsonObject;
    }
}
