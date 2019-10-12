package lyr.activemq.producer;

import com.alibaba.fastjson.JSONObject;
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
    @Qualifier("JmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${activemq.topic}")
    private String topic;

    @Value("${activemq.queue}")
    private String queue;

    @Value("${activemq.virtual.topic}")
    private String vTopic;


    /**
     * send msg to queue.
     * @param data
     */
    public void sendToQueue(Object data) {
        ActiveMQQueue mqQueue = new ActiveMQQueue(queue);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
//            msg.setStringProperty("data",JSONObject.toJSONString(data));
            msg.setStringProperty("data",data.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        jmsTemplate.convertAndSend(mqQueue, msg);
    }

    /**
     * send msg to topic.
     * @param data
     */
    public void sendToTopic(Object data) {
        ActiveMQTopic mqTopic = new ActiveMQTopic(topic);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
            msg.setStringProperty("data",JSONObject.toJSONString(data));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        jmsTemplate.convertAndSend(mqTopic, msg);
    }

    /**
     * send msg to virtual topic.
     * @param data
     */
    /*public void sendToVTopic(Object data) {
        ActiveMQTopic mqVTopic = new ActiveMQTopic(vTopic);
        ActiveMQMessage msg = new ActiveMQMessage();
        try {
            msg.setStringProperty("data",JSONObject.toJSONString(data));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        jmsTemplate.convertAndSend(mqVTopic, msg);
    }*/
}
