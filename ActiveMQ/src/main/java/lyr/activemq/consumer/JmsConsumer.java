package lyr.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消费者
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/25 15:14
 * @Version 1.0
 **/
@Component
@Transactional(rollbackFor = Exception.class)
public class JmsConsumer {

    private Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "${activemq.topic}", containerFactory = "TopicListener")
    @Async // 异步
    public void receiveTopic1(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": topic1===========" + msg.getStringProperty("data"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.topic}", containerFactory = "TopicListener")
    @Async
    public void receiveTopic2(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": topic2===========" + msg.getStringProperty("data"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.queue}", containerFactory = "QueueListener")
    @Async
    public void receiveQueue1(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": Queue1===========" + msg.getStringProperty("data"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.queue}", containerFactory = "QueueListener")
    @Async
    public void receiveQueue2(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": Queue2===========" + msg.getStringProperty("data"));
        msg.acknowledge(); //消息确认
    }

    //消息重发test
    /*@JmsListener(destination = "${activemq.queue}", containerFactory = "QueueListener")
    @Async
    public void receiveQueue3(Message msg, Session session) throws JMSException {
        try {
            logger.info("Consumer收到的报文为:" + msg.getStringProperty("data"));
            if("发送给Queue的第5条消息".equals(msg.getStringProperty("data"))){
                logger.info("准备报错");
                int i = 1/0;
            }
            msg.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
    }*/


    /*@JmsListener(destination = "${activemq.virtual.topic.A}", containerFactory = "QueueListener")
    @Async
    public void receiveVTopicA1(Message msg) throws JMSException {
        logger.info(thread.currentThread().getName() + ": vtopic A1===========" + msg.getStringProperty("data"));
    }*/

    /*@JmsListener(destination = "${activemq.virtual.topic.A}", containerFactory = "QueueListener")
    @Async
    public void receiveVTopicA2(Message msg) throws JMSException {
        logger.info(thread.currentThread().getName() + ": vtopic A2===========" + msg.getStringProperty("data"));
    }*/

    /*@JmsListener(destination = "${activemq.virtual.topic.B}", containerFactory = "QueueListener")
    @Async
    public void receiveVTopicB(Message msg) throws JMSException {
        logger.info(thread.currentThread().getName() + ": vtopic B===========" + msg.getStringProperty("data"));
    }*/
}
