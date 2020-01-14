package lyr.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写锁测试
 * @ClassName WriteLock
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/13 17:00
 * @Version 1.0
 **/
public class WriteLock {

    private Lock writeLock = new ReentrantReadWriteLock().writeLock();

    public static void main(String[] args) {

        WriteLock writeLock = new WriteLock();

        new Thread(() -> writeLock.example1(Thread.currentThread())).start();
        new Thread(() -> writeLock.example1(Thread.currentThread())).start();
    }

    void example1(Thread thread){
        writeLock.lock();
        try {
            System.out.println(thread.getName()+"获取写锁");
            System.out.println(thread.getName()+"正在写");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName()+"释放写锁");
            writeLock.unlock();
        }

    }
}
