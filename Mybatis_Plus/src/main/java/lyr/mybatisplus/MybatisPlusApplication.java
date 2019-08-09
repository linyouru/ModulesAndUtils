package lyr.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusApplication
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/29 9:04
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("lyr.mybatisplus.dao")   //映射mybatis的mapper接口所在的包
@EnableTransactionManagement //开启事务支持
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class);
        System.out.println("================项目启动完成=================");
    }

}
