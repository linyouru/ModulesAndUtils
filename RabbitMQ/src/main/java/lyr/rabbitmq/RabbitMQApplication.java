package lyr.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class, args);
        System.out.println("================项目启动完成=================");
    }

}
