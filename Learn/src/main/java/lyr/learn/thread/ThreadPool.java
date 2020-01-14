package lyr.learn.thread;


import java.util.ArrayList;
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

        example2();
    }

    /**
     * 使用Executors预定义线程池
     * @Author LinYouRu
     * @Date 11:05 2020/1/9
     * @return java.util.concurrent.ExecutorService
     **/
    static ExecutorService newThreadPool(){

        //固定线程池
        ExecutorService pool1 = Executors.newFixedThreadPool(5);
        //单个线程线程池
        ExecutorService pool2 = Executors.newSingleThreadExecutor();
        //缓存线程池
        ExecutorService pool3 = Executors.newCachedThreadPool();
        //定时线程池
        ExecutorService pool4 = Executors.newScheduledThreadPool(0);
        return pool1;
    }
    
    /**
     * 自定义线程池
     * @Author LinYouRu
     * @Date 11:06 2020/1/9
     * @return java.util.concurrent.ExecutorService
     **/
    static ExecutorService creatThreadPool(){
        //自定义线程池
        ExecutorService pool = new ThreadPoolExecutor(2,2,1,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),new ThreadPoolExecutor.DiscardPolicy());
        return pool;
    }

    /**
     * 处理线程异常
     * @Author LinYouRu
     * @Date 11:07 2020/1/9
     * @return void
     **/
    static void example1(){
        ExecutorService pool = creatThreadPool();
        Future<Object> future = pool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                throw new Exception("抛出异常");
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

    }

    /**
     * 处理返回结果
     * @Author LinYouRu
     * @Date 11:30 2020/1/9
     * @return void
     **/
    static void example2(){
        ExecutorService pool = creatThreadPool();
        Future<Object> future = pool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                ArrayList<String> list = new ArrayList<>();
                list.add("A");
                return list;
            }
        });
        try {
            ArrayList<String> list = (ArrayList) future.get();
            for (String s : list) {
                System.out.println(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

    }
    
}
