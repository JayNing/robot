package com.zx.robot.controller;

import com.zx.common.entity.ReturnMsg;
import com.zx.common.redis.RedisCacheUtil;
import com.zx.robot.entity.InspectionData;
import com.zx.robot.schedule.RefreshRobotInfoTimer;
import com.zx.robot.service.InspectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequestMapping("client")
@Controller
public class ClientController {


    @Autowired
    private RefreshRobotInfoTimer refreshRobotInfoTimer;
    @Autowired
    private InspectionDataService inspectionDataService;

    @Autowired
    private RedisCacheUtil redisCache;

    @RequestMapping("addRedis")
    public @ResponseBody ReturnMsg redis(HttpServletResponse response){
        ReturnMsg msg = new ReturnMsg();

        redisCache.sSet("demo","张三", null,null);
        redisCache.sSet("demoTime","李四", 60L,TimeUnit.SECONDS);

        msg.setData("存储redis成功！");
        msg.setErrorCode(0);
        return msg;
    }

    @RequestMapping("searchRedis")
    public @ResponseBody ReturnMsg searchRedis(HttpServletResponse response){
        ReturnMsg msg = new ReturnMsg();

        String demo = redisCache.sGet("demo");
        String demoTime = redisCache.sGet("demoTime");

        msg.setData("demo : " + demo + ", demoTime = " + demoTime);
        msg.setErrorCode(0);
        return msg;
    }

    @RequestMapping("start")
    public @ResponseBody ReturnMsg start(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin","*");
//        new Thread( () -> {
//            try {
//                // 1.创建 socket 指定服务器地址和端
//                Socket client = new Socket("127.0.0.1", 8001);
//                // 2.客户端向服务器发送登录信息
//                OutputStream os = client.getOutputStream();// 字节输出流
//                PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);
//                pw.write("start");
//                pw.flush();
//                client.shutdownOutput();// 关闭输出流
//
//                // 3. 获取输入流
//                InputStream is = client.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is,"UTF-8");
//                BufferedReader br = new BufferedReader(isr);
//                String info = null;
//                while ((info = br.readLine()) != null) {
//                    System.out.println("服务器发来消息说：" + info);
//                }
//
//                // 3.关闭其他资源
//                pw.close();
//                os.close();
//                client.close();
//            } catch (UnknownHostException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }).start();

        refreshRobotInfoTimer.patrolTime = System.currentTimeMillis();
      //模拟机器人收到客户端通知，开始每3秒上报一次数据到数据库
        new Thread( () -> {
            for (double j = 0; j < 100; j++){
                double i = j + Math.random() * 100;
                InspectionData inspectionData = new InspectionData(i + 50,i,i + 50,i,i + 50,i,i,i,i,i,i,i,"a_" + i, new Date());
                inspectionDataService.insert(inspectionData);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();
        ReturnMsg msg = new ReturnMsg();
        msg.setErrorCode(0);
        msg.setData("向机器人发送开始指令成功！");
        return msg;
    }

}