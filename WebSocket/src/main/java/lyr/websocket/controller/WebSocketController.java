package lyr.websocket.controller;

import lyr.websocket.service.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName WebSocketController
 * @Description TODO
 * @Author LYR
 * @Date 2019/8/7 14:51
 * @Version 1.0
 **/
@RestController
public class WebSocketController {

    @Resource
    private WebSocketServer webSocket;

    @RequestMapping("/getMsg")
    public void getMsg(){

        try {
            webSocket.sendMessageBySid("123123123","001");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendAllMsg")
    public void sendAllMsg(){
        try {
            webSocket.sendAllMessage("111111111");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
