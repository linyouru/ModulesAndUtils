package lyr.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
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
@EnableAsync // 启用异步任务
@EnableJms
public class JmsConfiguration {

    private Logger logger = LoggerFactory.getLogger(JmsConfiguration.class);

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.user}")
    private String userName;
    @Value("${spring.activemq.password}")
    private String password;
    @Value("${spring.activemq.prefix}")
    private String prefix;


    /**连接工厂*/
    @Bean(name = "ConnectionFactory")
    public ActiveMQConnectionFactory getFirstConnectionFactory(RedeliveryPolicy redeliveryPolicy)
    {
        logger.debug(brokerUrl + " - " + userName);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        //设置消息重发
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return connectionFactory;
    }

    @Bean(name = "JmsTemplate")
    public JmsTemplate getFirstJmsTemplate(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setDeliveryMode(2);
        jmsTemplate.setConnectionFactory(connectionFactory);
        //客户端签收模式
        jmsTemplate.setSessionAcknowledgeMode(4);
        return jmsTemplate;
    }

    /**监听器*/
    @Bean(name = "TopicListener")
    public DefaultJmsListenerContainerFactory getFirstTopicListener(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // if topic, set true
        factory.setPubSubDomain(true);
        // factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }

    /**监听器*/
    @Bean(name = "QueueListener")
    public DefaultJmsListenerContainerFactory getFirstQueueListener(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置连接数
        factory.setConcurrency("1-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        // change acknowledge mode
        factory.setSessionAcknowledgeMode(4);
        return factory;
    }

    /**
     * 消息重发机制设置
     * @Author LinYouRu
     * @Date 9:12 2019/10/12
     * @return org.apache.activemq.RedeliveryPolicy
     **/
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次，超过重发次数后消息将被放入死信队列
        redeliveryPolicy.setMaximumRedeliveries(6);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(1);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }

    /**
     * 配置自定义的注册器
     * @return
     */
    /*@Bean
    public CustomJmsListenerEndpointRegistry myJmsListenerEndpointRegistry() {
        CustomJmsListenerEndpointRegistry myJmsListenerEndpointRegistry = new CustomJmsListenerEndpointRegistry();
        myJmsListenerEndpointRegistry.setPrefix(prefix);
        return myJmsListenerEndpointRegistry;
    }*/
}
