package lyr.learn.pattern.singleton;

import java.io.Serializable;

/**
 * 单例模式（饿汉）
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 * @ClassName HungrySingleton
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/23 14:19
 * @Version 1.0
 **/
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }
}

/**
 * 饿汉式的改进型
 * @Author LinYouRu
 * @Date 15:05 2020/1/3
 * @return
 **/
class StaticInnerClassSingleton {

    //在静态内部类中初始化实例对象
    private static class SingletonHolder{
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    //私有化构造方法
    private StaticInnerClassSingleton(){

    }

    //对外提供获取实例的静态方法
    public static final StaticInnerClassSingleton getInstance(){
        return SingletonHolder.instance;
    }


}