package lyr.rabbitmq.consumer;

import lyr.rabbitmq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MsgReceiverC
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/19 9:25
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_C)
public class MsgReceiverC {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列C当中的消息： {}" , content);
    }
}
