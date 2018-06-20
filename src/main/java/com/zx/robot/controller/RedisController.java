package com.zx.robot.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zx.common.entity.ReturnMsg;
import com.zx.robot.entity.InspectionData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 测试redis实现session共享
 *
 * @author ning
 * @create 2018-06-20 10:12
 **/
@RequestMapping("redis")
@Controller
public class RedisController {
    private final Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();

    @RequestMapping(value = "login")
    public @ResponseBody
    ReturnMsg login(HttpServletRequest request, String username){
        ReturnMsg msg = new ReturnMsg();
        double i = 1D;
        InspectionData inspectionData = new InspectionData(i + 50,i,i + 50,i,i + 50,i,i,i,i,i,i,i,request.getSession().getId(), new Date());
        request.getSession().setAttribute("msg_user", gson.toJson(inspectionData));

        msg.setData(inspectionData);
        msg.setErrorCode(0);
        return msg;
    }

    @RequestMapping(value = "index")
    public @ResponseBody
    ReturnMsg index(HttpServletRequest request){
        ReturnMsg msg = new ReturnMsg();
        InspectionData user = gson.fromJson(request.getSession().getAttribute("msg_user").toString(), InspectionData.class);

        msg.setData(user);
        msg.setErrorCode(0);

        return msg;
    }
}