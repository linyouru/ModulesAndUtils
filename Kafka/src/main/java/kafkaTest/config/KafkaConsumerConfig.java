package kafkaTest.config;


import kafkaTest.common.MessageEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.servers}")
    private String servers;
    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;
    @Value("${kafka.consumer.session.timeout}")
    private String sessionTimeout;
    @Value("${kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;
    @Value("${kafka.consumer.group.id}")
    private String groupId;
    @Value("${kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;
    @Value("${kafka.consumer.concurrency}")
    private int concurrency;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageEntity>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    private ConsumerFactory<String, MessageEntity> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                //配置内容反序列化
                new JsonDeserializer<>(MessageEntity.class)
        );
    }


    private Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        /** auto.offset.reset 参数  从什么时候开始消费
         *  public static final String AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset";
         *
         *  这个参数是针对新的groupid中的消费者而言的，当有新groupid的消费者来消费指定的topic时，对于该参数的配置，会有不同的语义
         *  auto.offset.reset=latest情况下，新的消费者将会从其他消费者最后消费的offset处开始消费topic下的消息
         *  auto.offset.reset= earliest情况下，新的消费者会从该topic最早的消息开始消费
         auto.offset.reset=none情况下，新的消费组加入以后，由于之前不存在 offset，则会直接抛出异常。说白了，新的消费组不要设置这个值
         */

        //enable.auto.commit
        //消费者消费消息以后自动提交，只有当消息提交以后，该消息才不会被再次接收到（如果没有 commit，消息可以重复消费，也没有 offset），还可以配合auto.commit.interval.ms控制自动提交的频率。
        //当然，我们也可以通过consumer.commitSync()的方式实现手动提交
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        //反序列化 key
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //反序列化 value
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //消费组
        /**
         * consumer group是kafka提供的可扩展且具有容错性的消费者机制。既然是
         一个组，那么组内必然可以有多个消费者或消费者实例((consumer instance),
         它们共享一个公共的ID，即group ID。组内的所有消费者协调在一起来消费订
         阅主题(subscribed topics)的所有分区(partition)。当然，每个分区只能由同一
         个消费组内的一个consumer来消费.后面会进一步介绍。
         */
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        /**max.poll.records
         *此参数设置限制每次调用poll返回的消息数，这样可以更容易的预测每次poll间隔
         要处理的最大值。通过调整此值，可以减少poll间隔
         */
        //间隔时间
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return propsMap;
    }
}