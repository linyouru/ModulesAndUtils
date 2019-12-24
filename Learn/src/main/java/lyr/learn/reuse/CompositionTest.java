package lyr.learn.reuse;

/**
 * 使用组合实现
 * @ClassName CompositionTest
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/24 15:32
 * @Version 1.0
 **/
public class CompositionTest {
    public static void main(String[] args) {

        Computer computer = new Computer();
        CPU cpu = new CPU(computer);
        GPU gpu = new GPU(computer);

        cpu.count();
        cpu.run();
        gpu.count();
        gpu.run();

    }
}


class Computer{

    private void describe(){
        System.out.println("电脑用电");
    }

    public void count(){
        describe();
        System.out.println("电脑可以计算");
    }

}


class CPU{

    /**
     * 定义一个Computer成员变量，以供组合之用
     **/
    private Computer computer;

    /**
     * 使用构造函数初始化成员变量
     **/
    public CPU(Computer computer) {
        this.computer = computer;
    }

    /**
     * 通过调用成员变量的固有方法Computer.count(),使新类具有相同的功能count()
     **/
    public void count(){
        computer.count();
    }

    /**
     *为新类增加新的方法
     **/
    public void run(){
        System.out.println("CPU处理运算");
    }
}

class GPU{

    private Computer computer;

    public GPU(Computer computer) {
        this.computer = computer;
    }

    public void count(){
        computer.count();
    }

    public void run(){
        System.out.println("GPU渲染图形");
    }
}