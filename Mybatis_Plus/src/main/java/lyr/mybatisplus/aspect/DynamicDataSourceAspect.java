package lyr.mybatisplus.aspect;

import java.lang.reflect.Method;

import lyr.mybatisplus.config.DynamicDataSourceHolder;
import lyr.mybatisplus.config.TargetDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * @ClassName DynamicDataSourceAspect
 * @Description TODO 定义切面用于切换数据源
 * @Author LYR
 * @Date 2019/8/9 15:22
 * @Version 1.0
 **/
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(lyr.mybatisplus.config.TargetDataSource)")
    public void point() {
    }

    @Pointcut("execution(public * lyr.mybatisplus.dao.*.*(..))")
    public void excudeService() {
    }

    @Around(value = "point()&&excudeService()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
            DynamicDataSourceHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();// 执行方法
        DynamicDataSourceHolder.clearDataSource();
        return result;
    }

}
