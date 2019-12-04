package lyr.learn.thread;

/**
 * Thread实现多线程
 * @ClassName MyThread
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/3 8:51
 * @Version 1.0
 **/
public class MyThread {


    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread1().start();
        }
    }

}

class Thread1 extends Thread{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

}
