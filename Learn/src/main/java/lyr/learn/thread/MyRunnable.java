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
        new Thread(new Runnable1("线程3")).start();
        new Thread(new Runnable1("线程4")).start();
    }
}


class Runnable1 implements Runnable{

    private String name;

    public Runnable1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(name+"：运行"+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}