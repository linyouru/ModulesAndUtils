package lyr.learn.pattern.singleton;

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

    public HungrySingleton getInstance(){
        return instance;
    }
}
