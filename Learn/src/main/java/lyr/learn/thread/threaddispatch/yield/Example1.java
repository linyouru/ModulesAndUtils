package lyr.learn.thread.threaddispatch.yield;

/**
 * 线程调度-yield
 * @ClassName Example1
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/8 11:16
 * @Version 1.0
 **/
public class Example1 {

    public static void main(String[] args) {
        ThreadT threadT1 = new ThreadT("线程1");
        ThreadT threadT2 = new ThreadT("线程2");

        threadT1.start();
        threadT2.start();
    }    
}

class ThreadT extends Thread{

    private String name;

    public ThreadT(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0;i<50;i++){
            System.out.println(name+"："+i);
            //当i=25的时候当前线程让出cpu
            if(i==25){
               yield();
            }
        }
    }
}