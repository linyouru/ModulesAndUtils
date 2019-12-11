package lyr.jdbctemplat.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 数据源配置类
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/11 16:03
 * @Version 1.0
 **/

@Configuration
public class DataSourceConfig {

    /**配置数据源1*/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource dsone(){
        return DruidDataSourceBuilder.create().build();
    }

    /**配置数据源2*/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource dstwo(){
        return DruidDataSourceBuilder.create().build();
    }
}
