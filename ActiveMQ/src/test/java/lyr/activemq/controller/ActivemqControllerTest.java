package lyr.activemq.controller;

import lyr.activemq.ActiveMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ActiveMQApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// 指定启动类
@ContextConfiguration
public class ActivemqControllerTest {

    @Resource
    ActivemqController controller;

    @Test
    public void sendToQueue() {
        controller.sendToQueue();
    }

    @Test
    public void sendToTopic() {
        controller.sendToTopic();
    }

    @Test
    public void sendToVTopic() {
    }
}