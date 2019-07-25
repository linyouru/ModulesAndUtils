package lyr.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.jms.ConnectionFactory;

/**
 * JMS规范的ack消息确认机制有一下四种，定于在session对象中：
 * AUTO_ACKNOWLEDGE = 1 ：自动确认
 * CLIENT_ACKNOWLEDGE = 2：客户端手动确认
 * DUPS_OK_ACKNOWLEDGE = 3： 自动批量确认
 * SESSION_TRANSACTED = 0：事务提交并确认
 * 但是在activemq补充了一个自定义的ACK模式:
 * INDIVIDUAL_ACKNOWLEDGE = 4：单条消息确认
 * @ClassName JmsConfiguration
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/25 14:50
 * @Version 1.0
 **/
@Configuration
@EnableAsync // enable asynchronous task
@EnableJms
public class JmsConfiguration {

    private Logger logger = LoggerFactory.getLogger(JmsConfiguration.class);

    @Bean(name = "firstConnectionFactory")
    public ActiveMQConnectionFactory getFirstConnectionFactory(@Value("${spring.activemq.broker-url}") String brokerUrl,
                                                               @Value("${spring.activemq.user}") String userName, @Value("${spring.activemq.password}") String password)
    {
        logger.debug(brokerUrl + " - " + userName);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "firstJmsTemplate")
    public JmsTemplate getFirstJmsTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean(name = "firstTopicListener")
    public DefaultJmsListenerContainerFactory getFirstTopicListener(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // if topic, set true
        // factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }

    @Bean(name = "firstQueueListener")
    public DefaultJmsListenerContainerFactory getFirstQueueListener(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }
}
