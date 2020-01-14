package lyr.learn.thread.lock;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly()测试
 * @ClassName LockInterruptiblyDemo
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/13 16:13
 * @Version 1.0
 **/
public class LockInterruptiblyDemo {

    private Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        LockInterruptiblyDemo demo = new LockInterruptiblyDemo();

        Thread thread1 = new Thread(new Runner(demo));
        Thread thread2 = new Thread(new Runner(demo));

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    void example1(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName()+"获得了锁");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName()+"释放了锁");
        }

    }

}

class Runner implements Runnable{

    private LockInterruptiblyDemo demo;

    public Runner(LockInterruptiblyDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            demo.example1(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
        }

    }
}
