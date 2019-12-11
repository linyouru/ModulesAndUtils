package lyr.jdbctemplat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName lyr.jdbctemplat.JdbcTemplatApplication
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/10 19:43
 * @Version 1.0
 **/
@SpringBootApplication
public class JdbcTemplatApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplatApplication.class);
        System.out.println("================项目启动完成=================");
    }
}
