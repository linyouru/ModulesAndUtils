package lyr.learn.java8.first;

/**
 * 接口的默认方法
 * Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法
 * @ClassName First
 * @Description TODO
 * @Author LYR
 * @Date 2019/10/16 10:21
 * @Version 1.0
 **/
public interface First {

    String test1(String a);

    default String test2(String b){
        return b+"-lyr";
    }

}

class FirstImpl implements First{

    @Override
    public String test1(String a) {
        return a+"ll";
    }

}

class FirstTest{

    public static void main(String[] args) {
        FirstImpl first = new FirstImpl();
        System.out.println(first.test1("test1"));
        System.out.println(first.test2("test2"));
    }
}