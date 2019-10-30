package lyr.learn.Java8.lambda;

import java.util.*;
import java.util.function.Predicate;

/**
 * Lambda 表达式
 * @ClassName Lambda
 * @Description TODO
 * @Author LYR
 * @Date 2019/10/16 10:30
 * @Version 1.0
 **/
public class Lambda {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("2","6","9","22","5");

        list.stream().filter((s)-> Integer.valueOf(s)>8).forEach(System.out::println);
    }

}
