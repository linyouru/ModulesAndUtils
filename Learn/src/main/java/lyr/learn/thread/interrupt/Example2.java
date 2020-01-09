package lyr.learn.thread.interrupt;

/**
 * 死锁状态线程无法被中断
 * @ClassName Example2
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/8 14:47
 * @Version 1.0
 **/
public class Example2 extends Thread{

    public static void main(String[] args) throws InterruptedException {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread t1 = new Thread(() -> deathLock(lock1,lock2));

        Thread t2 = new Thread(() -> {
            //这里换了位置
            deathLock(lock2,lock1);
        });

        System.out.println("开启线程");
        t1.start();
        t2.start();
        Thread.sleep(2000);
        System.out.println("阻塞线程");
        t1.interrupt();
        t2.interrupt();
        Thread.sleep(2000);
        System.out.println("停止主线程");

    }

    static void deathLock(Object lock1,Object lock2){
        try {
            synchronized (lock1){
                //不会在这里死锁
                Thread.sleep(1000);
                synchronized (lock2){ //会在这里死锁,虽然阻塞了，但是不会抛异常
                    System.out.println(Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
