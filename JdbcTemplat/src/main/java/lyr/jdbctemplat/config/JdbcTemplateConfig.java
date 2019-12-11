package lyr.jdbctemplat.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Jdbctemplate配置类
 * @ClassName JdbcTemplate
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/11 16:15
 * @Version 1.0
 **/
@Configuration
public class JdbcTemplateConfig {

    @Bean
    JdbcTemplate jdbcTemplateOne(@Qualifier("dsone")DataSource dsone){
        return new JdbcTemplate(dsone);
    }

    @Bean
    JdbcTemplate jdbcTemplatetwo(@Qualifier("dstwo")DataSource dstwo){
        return new JdbcTemplate(dstwo);
    }

}
