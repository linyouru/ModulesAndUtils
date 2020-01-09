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
        Thread1 threadT1 = new Thread1("线程1");
        Thread1 threadT2 = new Thread1("线程2");

        threadT1.start();
        threadT2.start();
    }

}

class Thread1 extends Thread{

    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(name+"：运行"+i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
