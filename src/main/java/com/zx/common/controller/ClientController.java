package com.zx.common.controller;

import com.zx.common.entity.ReturnMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

@RequestMapping("client")
@Controller
public class ClientController {

    @RequestMapping("start")
    public @ResponseBody ReturnMsg start(){
        try {
            // 1.创建 socket 指定服务器地址和端
            Socket client = new Socket("127.0.0.1", 8001);
            // 2.客户端向服务器发送登录信息
            OutputStream os = client.getOutputStream();// 字节输出流
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);
            pw.write("用户名: admin;密码：123");
            pw.flush();
            client.shutdownOutput();// 关闭输出流

            // 3. 获取输入流
            InputStream is = client.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("服务器发来消息说：" + info);
            }

            // 3.关闭其他资源
            pw.close();
            os.close();
            client.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ReturnMsg msg = new ReturnMsg();
        msg.setErrorCode(0);
        msg.setData("向机器人发送开始指令成功！");
        return msg;
    }

}