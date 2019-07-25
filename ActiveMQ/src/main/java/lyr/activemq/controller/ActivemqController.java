package lyr.activemq.controller;

import lyr.activemq.producer.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ActivemqController
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/25 15:39
 * @Version 1.0
 **/
@Controller
public class ActivemqController {

    @Resource
    private JmsProducer producer;

    @RequestMapping("/sendToQueue.do")
    public void sendToQueue(){
        for (int i=0;i<10;i++){
            producer.sendToQueue("发送给Queue的第"+i+"条消息");
        }
    }

    @RequestMapping("/sendToTopic.do")
    public void sendToTopic(){
        for (int i=0;i<10;i++){
            producer.sendToTopic("发送给Topic的第"+i+"条消息");
        }
    }

    @RequestMapping("/sendToVTopic.do")
    public void sendToVTopic(){
        for (int i=0;i<10;i++){
            producer.sendToVTopic("发送给VTopic的第"+i+"条消息");
        }
    }


}
