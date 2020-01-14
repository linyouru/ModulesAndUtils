package lyr.learn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock()测试
 * @ClassName LockDemo
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/13 9:10
 * @Version 1.0
 **/
public class LockDemo {


    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();

        new Thread(() -> lockDemo.example1(Thread.currentThread())).start();
        new Thread(() -> lockDemo.example1(Thread.currentThread())).start();

    }

    void example1(Thread thread){
       lock.lock();

        try {
            System.out.println(thread.getName()+"获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }

    }


}
