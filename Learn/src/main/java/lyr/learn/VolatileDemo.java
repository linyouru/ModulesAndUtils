package lyr.learn;

public class VolatileDemo {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    //volatile关键字不保证操作的原子性测试
    static void test1() {
        final VolatileDemo m = new VolatileDemo();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        m.increase();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(m.inc);
    }


    public static void main(String[] args) {
        VolatileDemo.test1();
    }
}
