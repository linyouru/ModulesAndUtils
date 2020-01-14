package lyr.learn.thread.lock;


/**
 * 一个简单的死锁
 * @ClassName DeadLockDemo
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/9 11:53
 * @Version 1.0
 **/
public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {

        Object a = new Object();
        Object b = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程启动");
                deadLock(a,b);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程启动");
                deadLock(b,a);
            }
        }).start();
    }


    static void deadLock(Object a,Object b){
        try {
            synchronized (a){
                System.out.println(Thread.currentThread().getName()+"获取对象"+a.toString()+"的锁");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"等待对象"+b.toString()+"的锁");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"获取对象"+b.toString()+"的锁");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
