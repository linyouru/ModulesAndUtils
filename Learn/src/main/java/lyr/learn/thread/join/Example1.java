package lyr.learn.thread.join;

/**
 * 线程调度-join
 * @ClassName Example1
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/8 10:50
 * @Version 1.0
 **/
public class Example1 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程开始运行");
        Thread thread1 = new Thread(new Runnable1("线程a"));
        Thread thread2 = new Thread(new Runnable1("线程b"));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"主线程结束运行");
    }

}


class Runnable1 implements Runnable{

    private String name;

    public Runnable1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+"开始运行");
        for (int i=0;i<10;i++) {
            System.out.println(name+"：运行"+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+"结束运行");
    }
}