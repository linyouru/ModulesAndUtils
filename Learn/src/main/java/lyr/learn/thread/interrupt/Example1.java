package lyr.learn.thread.interrupt;

/**
 * 线程调度-interrupt
 * @ClassName Example1
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/8 15:01
 * @Version 1.0
 **/
public class Example1 {

    public static void main(String[] args) throws InterruptedException {

       /* ThreadA thread = new ThreadA();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();*/

        ThreadB threadB = new ThreadB();
        threadB.start();
        Thread.sleep(3000);
        threadB.interrupt();

        System.out.println(Thread.currentThread().getName()+"主线程结束");
    }
}

class ThreadA extends Thread{
    @Override
    public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                //interrupt只是修改了中断标识，具体要看程序怎么处理
                System.out.println("中断标识为true，但线程仍在运行");
                return;
            }else {
                System.out.println("中断标识为false");
            }

        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}