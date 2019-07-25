package lyr.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * 消费者
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/25 15:14
 * @Version 1.0
 **/
@Component
public class JmsConsumer {

    private Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "${activemq.topic}", containerFactory = "firstTopicListener")
    @Async // receive msg asynchronously
    public void receiveTopic1(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": topic1===========" + msg.getStringProperty("value"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.topic}", containerFactory = "firstTopicListener")
    @Async // receive msg asynchronously
    public void receiveTopic2(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": topic2===========" + msg.getStringProperty("value"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.queue}", containerFactory = "firstQueueListener")
    @Async
    public void receiveQueue1(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": Queue1===========" + msg.getStringProperty("value"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.queue}", containerFactory = "firstQueueListener")
    @Async
    public void receiveQueue2(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": Queue2===========" + msg.getStringProperty("value"));
        msg.acknowledge(); //消息确认
    }

    @JmsListener(destination = "${activemq.virtual.topic.A}", containerFactory = "firstQueueListener")
    @Async
    public void receiveVTopicA1(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": vtopic A1===========" + msg.getStringProperty("value"));
    }

    @JmsListener(destination = "${activemq.virtual.topic.A}", containerFactory = "firstQueueListener")
    @Async
    public void receiveVTopicA2(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": vtopic A2===========" + msg.getStringProperty("value"));
    }

    @JmsListener(destination = "${activemq.virtual.topic.B}", containerFactory = "firstQueueListener")
    @Async
    public void receiveVTopicB(Message msg) throws JMSException {
        logger.info(Thread.currentThread().getName() + ": vtopic B===========" + msg.getStringProperty("value"));
    }
}
