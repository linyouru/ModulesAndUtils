package lyr.learn.thread;

import java.util.concurrent.*;

/**
 * Callable实现多线程
 * 自从Java 1.5开始，提供了Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果。
 * @ClassName MyCallable
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/3 9:41
 * @Version 1.0
 **/
public class MyCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //创建连接池
        //不建议使用Executors创建线程池,有oom风险
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i=0;i<10;i++){
            Callable<Double> callable1 = new Callable1();
            FutureTask futureTask =new FutureTask(callable1);
            new Thread(futureTask).start(); //不建议显示使用线程
//            pool.submit(futureTask);
            threadPoolExecutor.submit(futureTask);
            System.out.println(Thread.currentThread().getName()+"--------"+futureTask.get());
        }
//        pool.shutdown();
        threadPoolExecutor.shutdown();
    }

}

class Callable1 implements Callable<Double>{

    @Override
    public Double call() throws Exception {

        System.out.println(Thread.currentThread().getName());
        return Math.random();
    }
}