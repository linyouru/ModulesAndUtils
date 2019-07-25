package lyr.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActiveMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class, args);
        System.out.println("================项目启动完成=================");
    }

}
