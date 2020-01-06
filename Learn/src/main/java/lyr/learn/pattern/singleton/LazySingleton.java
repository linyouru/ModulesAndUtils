package lyr.learn.pattern.singleton;

import java.io.Serializable;

/**
 * 单例（懒汉）
 * 如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
 * 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 * 该实现仍然存在序列化对单例的破坏
 * @ClassName LazySingleton
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/23 11:54
 * @Version 1.0
 **/
public class LazySingleton {

    private static volatile LazySingleton instance = null; //保证 instance 在所有线程中同步

    private LazySingleton() {} //private 避免类在外部被实例化

    public static LazySingleton getInstance(){
        //getInstance 方法前加同步
        if(instance ==null){
            synchronized (LazySingleton.class){
                if (instance==null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}


/**
 * 要想防止序列化对单例的破坏，只要在Singleton类中定义readResolve就可以解决该问题：
 * @Author LinYouRu
 * @Date 15:31 2020/1/3
 * @return
 **/
class LazySingleton1 implements Serializable {

    //保证 instance 在所有线程中同步
    private static volatile LazySingleton1 instance = null;

    private LazySingleton1() {} //private 避免类在外部被实例化

    public static LazySingleton1 getInstance(){
        //getInstance 方法前加同步
        if(instance ==null){
            synchronized (LazySingleton1.class){
                if (instance==null) {
                    instance = new LazySingleton1();
                }
            }
        }
        return instance;
    }

    private Object readResolve(){
        return instance;
    }
}