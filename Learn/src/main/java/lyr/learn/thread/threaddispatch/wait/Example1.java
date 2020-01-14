package lyr.learn.thread.threaddispatch.wait;

/**
 * 线程调度-wait
 * @ClassName Example1
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/8 15:27
 * @Version 1.0
 **/
public class Example1 implements Runnable{

    private String name;
    private Object prev;
    private Object self;

    private Example1(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count>0){
            synchronized (prev){ //获取前一个对象锁
                synchronized (self){//获取自身对象锁
                    System.out.print(name);
                    count--;
                    self.notify(); //唤醒等待自身锁得的线程
                }//同步代码块执行完成之后是释放自身对象锁
                try {
                    prev.wait();//释放前一个对象锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Example1 ea = new Example1("A",c,a);
        Example1 eb = new Example1("B",a,b);
        Example1 ec = new Example1("C",b,c);

        new Thread(ea).start();
        Thread.sleep(100);
        new Thread(eb).start();
        Thread.sleep(100);
        new Thread(ec).start();
        Thread.sleep(100);
    }
}
