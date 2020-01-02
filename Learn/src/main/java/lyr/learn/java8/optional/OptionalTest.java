package lyr.learn.java8.optional;

import java.util.Optional;

/**
 * Optional用于简化Java中对空值的判断处理，以防止出现各种空指针异常。
 * @ClassName OptionalTest
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/2 16:27
 * @Version 1.0
 **/
public class OptionalTest {

    public static void main(String[] args) {

        OptionalTest ot = new OptionalTest();
//        ot.ifPresentTest();
//        ot.isPresentTest();

        ot.filterTest();
    }

    /**
     * 创建Optional的方法
     * @Author LinYouRu
     * @Date 16:28 2020/1/2
     * @return void
     **/
    public void creatOptinal(){

        //1、用于创建一个空的Optional对象；其value属性为Null。
        Optional optional = Optional.empty();

        //2、传入的值必须是非空值，否则如果传入的值为空值，则会抛出空指针异常。
        Optional optional1 = Optional.of(1);

        //3、传入的值可以是空值，如果传入的值是空值，则与empty返回的结果是一样的
        Optional optional2 = Optional.ofNullable(null);

    }


    /**
     * ifPresent 当Value不为空时，执行传入的Consumer
     * @Author LinYouRu
     * @Date 16:54 2020/1/2
     * @return void
     **/
    public void ifPresentTest(){
        Optional.ofNullable(back()).ifPresent(System.out::println);
    }

    /**
     * Value是否为空值的判断
     * @Author LinYouRu
     * @Date 17:02 2020/1/2
     * @return void
     **/
    public void isPresentTest(){
        boolean present = Optional.ofNullable(back()).isPresent();
        System.out.println(present);
    }


    /**
     * 当Value为空或者传入的Predicate对象调用test(value)返回False时，返回Empty对象；否则返回当前的Optional对象
     * @Author LinYouRu
     * @Date 17:26 2020/1/2
     * @return void
     **/
    public void filterTest(){
        Optional<String> s = Optional.ofNullable(back()).filter("1"::equals);
        System.out.println(s.get());

    }


    private String back(){
        return "1";
    }


}
