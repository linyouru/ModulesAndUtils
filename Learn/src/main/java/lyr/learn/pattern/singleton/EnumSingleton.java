package lyr.learn.pattern.singleton;

/**
 * 枚举式单例，是实现单例的最佳方式，枚举底层实现了线程安全，同时避免了序列化对单例的破坏
 * @Author LinYouRu
 * @Date 15:24 2020/1/3
 * @return
 **/
public enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {
    }
}
