package lyr.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()测试
 * @ClassName TryLockDemo
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/13 16:06
 * @Version 1.0
 **/
public class TryLockDemo {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDemo tryLockDemo = new TryLockDemo();

        new Thread(() ->tryLockDemo.example1(Thread.currentThread())).start();
        new Thread(() ->tryLockDemo.example1(Thread.currentThread())).start();

    }

    void example1(Thread thread){

        if(lock.tryLock()){
            try {
                System.out.println(thread.getName()+"获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }

}
