
package lyr.mybatisplus.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * @ClassName DruidDataSourceConfig
 * @Description TODO(多数据源切换)
 * @Author LYR
 * @Date 2019/8/9 15:26
 * @Version 1.0
 **/

@Configuration
@MapperScan(basePackages ="lyr.mybatisplus.dao", sqlSessionFactoryRef = "mybatisSqlSessionFactoryBean")
@PropertySource(value = { "classpath:application.properties" })
public class DruidDataSourceConfig {


    /**
     * 配置别名
     */

    @Value("${mybatis-plus.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * 配置mapper的扫描，找到所有的mapper.xml映射文件
     */

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;


    /**
     * 加载全局的配置文件
     */

//    @Value("${mybatis-plus.config-location}")
//    private String configLocation;

    /**
     * 数据源1
     */

    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 数据源2
     */

    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 数据源管理
     */

    @Bean
    public DataSource dynamicDataSource() throws SQLException {
        DynamicDataSource dynmicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("oneDataSource", dataSourceOne());
        targetDataSources.put("twoDataSource", dataSourceTwo());
        dynmicDataSource.setTargetDataSources(targetDataSources);
        dynmicDataSource.setDefaultTargetDataSource(dataSourceOne());  //设置默认数据源
        return dynmicDataSource;
    }


    /**
     * SqlSessionFactory
     * mybatis-plus包与mybatis包还是有区别的，
     * sqlSessionFactory配置要将sqlSessionFactory替换成mybatis-plusd的MybatisSqlSessionFactoryBean
     * 否则mybatis-plus默认的CRUD无法使用
     */

    /*@Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
//        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        return sqlSessionFactoryBean.getObject();
    }*/

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(@Qualifier("dynamicDataSource")DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return mybatisSqlSessionFactoryBean;
    }


    /**
     * 事物
     */

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

