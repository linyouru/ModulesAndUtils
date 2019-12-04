package lyr.learn.thread;

import java.util.concurrent.*;

/**
 * Callable实现多线程
 * @ClassName MyCallable
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/3 9:41
 * @Version 1.0
 **/
public class MyCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //创建连接池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            Callable<Integer> callable1 = new Callable1();
            FutureTask futureTask =new FutureTask(callable1);
//            new Thread(futureTask).start(); //不建议显示使用线程
            pool.submit(futureTask);
            System.out.println(Thread.currentThread().getName()+"--------"+futureTask.get());
        }
        pool.shutdown();
    }

}

class Callable1 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName());
        return 0;
    }
}