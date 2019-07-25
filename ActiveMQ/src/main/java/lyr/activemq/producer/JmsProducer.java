package lyr.activemq.producer;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * 消息生产者
 * @ClassName JmsProducer
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/25 15:08
 * @Version 1.0
 **/
@Component
public class JmsProducer {

    @Resource
    @Qualifier("firstJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${activemq.topic}")
    private String topic;

    @Value("${activemq.queue}")
    private String queue;

    @Value("${activemq.virtual.topic}")
    private String vTopic;

    private void sendMsg(Destination destination, Message msg) {
        jmsTemplate.convertAndSend(destination, msg);
    }

    /**
     * send msg to queue.
     * @param data
     */
    public void sendToQueue(String data) {
        ActiveMQQueue mqQueue = new ActiveMQQueue(queue);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
            msg.setStringProperty("value", data);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        sendMsg(mqQueue, msg);
    }

    /**
     * send msg to topic.
     * @param data
     */
    public void sendToTopic(String data) {
        ActiveMQTopic mqTopic = new ActiveMQTopic(topic);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
            msg.setStringProperty("value", data);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        sendMsg(mqTopic, msg);
    }

    /**
     * send msg to virtual topic.
     * @param data
     */
    public void sendToVTopic(String data) {
        ActiveMQTopic mqVTopic = new ActiveMQTopic(vTopic);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
            msg.setStringProperty("value", data);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        sendMsg(mqVTopic, msg);
    }
}
