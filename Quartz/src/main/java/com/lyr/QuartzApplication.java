package com.lyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName QuartzApplication
 * @Description TODO
 * @Author LYR
 * @Date 2019/8/12 17:05
 * @Version 1.0
 **/
@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class);
        System.out.println("================项目启动完成=================");
    }
}
