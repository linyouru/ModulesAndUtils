package lyr.learn.java8.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lambda 表达式,函数式编程
 * @ClassName Lambda
 * @Description TODO
 * @Author LYR
 * @Date 2019/10/16 10:30
 * @Version 1.0
 **/
public class Lambda {

    public static void main(String[] args) {

        Lambda lambda = new Lambda();
//        lambda.filter();
//        lambda.map();
//        lambda.limit();
//        lambda.skip();
//        lambda.sorted();
//        lambda.distinct();
//        lambda.forEach();
        lambda.collect();

    }


    /****************************Stream相关操作****************************/


    /**
     * 创建stream
     * @Author LinYouRu
     * @Date 17:03 2019/12/31
     * @return void
     **/
    public void creatStream(){

        //method1
        List<String> list = Arrays.asList("2","6","9","22","5");
        Stream<String> stream1 = list.stream();
        //并行流
        Stream<String> stream = list.parallelStream();

        //method2
        Stream<String> stream2 = Stream.of("2","6","9","22","5");

        //method3
        Stream stream3 = Stream.empty();
    }

    /****************************中间操作****************************/

    /**
     * filter 方法用于通过设置的条件过滤出元素
     * @Author LinYouRu
     * @Date 17:17 2019/12/31
     * @return
     **/
    public void filter (){

        List<Integer> list = Arrays.asList(2,6,9,22,5);
        list.stream().filter(i -> i%2==0).forEach(System.out::println);

    }

    /**
     * map会将元素根据指定的Function接口来依次将元素转成另外的对象
     * @Author LinYouRu
     * @Date 17:26 2019/12/31
     * @return void
     **/
    public void map(){

        List<Integer> list = Arrays.asList(2,6,9,22,5);
        list.stream().map(i -> i.toString()+"[string]").forEach(System.out::println);

    }

    /**
     * limit 返回 Stream 的前面 n 个元素
     * @Author LinYouRu
     * @Date 17:33 2019/12/31
     * @return void
     **/
    public void limit(){

        List<String> list = Arrays.asList("2","6","9","22","5");
        list.stream().limit(3).forEach(System.out::println);

    }

    /**
     * skip 则是扔掉前 n 个元素
     * @Author LinYouRu
     * @Date 17:33 2019/12/31
     * @return void
     **/
    public void skip (){

        List<String> list = Arrays.asList("2","6","9","22","5");
        list.stream().skip (3).forEach(System.out::println);

    }

    /**
     * sorted 方法用于对流进行排序
     * @Author LinYouRu
     * @Date 17:35 2019/12/31
     * @return void
     **/
    public void sorted(){

        List<Integer> list = Arrays.asList(2,6,9,22,5);
        list.stream().sorted().forEach(System.out::println);

    }

    /**
     * distinct主要用来去重
     * @Author LinYouRu
     * @Date 11:24 2020/1/2
     * @return void
     **/
    public void distinct(){

        List<String> list = Arrays.asList("2","6","9","22","5","2","9");
        list.stream().distinct().forEach(System.out::println);

    }

    /****************************最终操作****************************/

    /**
     * forEach 用来迭代流中的每个元素
     * @Author LinYouRu
     * @Date 11:34 2020/1/2
     * @return void
     **/
    public void forEach(){

        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

    }

    /**
     * count 用来统计流中元素个数
     * @Author LinYouRu
     * @Date 11:45 2020/1/2
     * @return void
     **/
    public void count(){

        List<String> list = Arrays.asList("2","6","9","22","5","2","9");
        long count = list.stream().count();
        System.out.println(count);

    }

    /**
     * collect就是一个归约操作，可以接受各种方法作为参数，将流中的元素累积成一个汇总结果
     * @Author LinYouRu
     * @Date 11:48 2020/1/2
     * @return void
     **/
    public void collect(){

        List<String> list = Arrays.asList("2","6","9","22","5","2","9");
        List<String> collect = list.stream().filter(s -> !"2".equals(s)).collect(Collectors.toList());
        System.out.println(collect);

    }


}
