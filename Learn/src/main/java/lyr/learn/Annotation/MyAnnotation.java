package lyr.learn.Annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface MyAnnotation {

    int id() default -1;
    String msg() default "无消息";

}
