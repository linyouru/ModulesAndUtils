package kafkaTest.config;

import java.util.HashMap;
import java.util.Map;

import kafkaTest.common.MessageEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.producer.servers}")
    private String servers;
    @Value("${kafka.producer.retries}")
    private int retries;
    @Value("${kafka.producer.batch.size}")
    private int batchSize;
    @Value("${kafka.producer.linger}")
    private int linger;
    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;


    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        //Kafka 地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        /**发送端消息确认模式：
         *  0：消息发送给broker后，不需要确认（性能较高，但是会出现数据丢失，而且风险最大，因为当 server 宕机时，数据将会丢失）
         *  1：只需要获得集群中的 leader节点的确认即可返回
         *  -1/all:需要 ISR 中的所有的 Replica进行确认（集群中的所有节点确认），最安全的，也有可能出现数据丢失（因为 ISR 可能会缩小到仅包含一个 Replica）
         */
        props.put(ProducerConfig.ACKS_CONFIG, "0");
        //配置生产者发送失败后的重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        //设置 key的序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //设置 value 的序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, MessageEntity> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(),
                new StringSerializer(),
                new JsonSerializer<MessageEntity>());
    }

    @Bean
    public KafkaTemplate<String, MessageEntity> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
