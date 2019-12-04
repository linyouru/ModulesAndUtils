package lyr.learn.thread;


import java.util.concurrent.*;

/**
 * 线程池
 * @ClassName ThreadPool
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/3 9:56
 * @Version 1.0
 **/
public class ThreadPool {

    public static void main(String[] args) {

        //自定义线程池
        ExecutorService pool1 = new ThreadPoolExecutor(2,2,1,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),new ThreadPoolExecutor.DiscardPolicy());

        //固定线程池
        ExecutorService pool2 = Executors.newFixedThreadPool(5);



        Runnable1 runnable1 = new Runnable1();
        for (int i=0;i<10;i++){
//            pool2.submit(runnable1);
            pool1.submit(runnable1);
        }
        pool1.shutdown();

    }

}
