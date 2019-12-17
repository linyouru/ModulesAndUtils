package lyr.mybatisplus.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName TargetDataSource
 * @Description TODO(自定义切换数据源)
 * @Author LYR
 * @Date 2019/8/9 15:27
 * @Version 1.0
 **/


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
     String  MYSQL="oneDataSource";
     String  POSTGRESQL="twoDataSource";
    String dataSource();
}


