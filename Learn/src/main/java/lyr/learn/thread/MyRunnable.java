package lyr.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runnable实现多线程
 * @ClassName MyRunnable
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/3 9:30
 * @Version 1.0
 **/
public class MyRunnable {

    public static void main(String[] args) {
        Runnable1 runnable1 = new Runnable1();
        //创建连接池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
//            new Thread(runnable1).start();//不建议显示使用线程
            pool.submit(runnable1);
        }
        pool.shutdown();
    }

}


class Runnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}