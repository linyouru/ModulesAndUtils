package lyr.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁测试
 * @ClassName ReadLock
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/13 16:50
 * @Version 1.0
 **/
public class ReadLock {

    private Lock readLock = new ReentrantReadWriteLock().readLock();

    public static void main(String[] args) {
        ReadLock readLock = new ReadLock();

        new Thread(() -> readLock.example1(Thread.currentThread())).start();
        new Thread(() -> readLock.example1(Thread.currentThread())).start();

    }

    void example1(Thread thread){
        readLock.lock();
        try {
            System.out.println(thread.getName()+"获取读锁");
            System.out.println(thread.getName()+"正在读");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName()+"释放读锁");
            readLock.unlock();
        }
    }

}
