package lyr.learn.reuse;

/**
 * 使用继承实现
 * @ClassName InheritanceTest
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/24 15:19
 * @Version 1.0
 **/
public class InheritanceTest {
    public static void main(String[] args) {
        Fish fish = new Fish();
        Dog dog = new Dog();

        fish.breath();
        fish.swim();
        dog.breath();
        dog.run();

    }
}

class Animal{

    private void beat(){
        System.out.println("动物有心跳");
    }

    public void breath(){
        beat();
        System.out.println("动物会呼吸");
    }
}

/**
 * 继承Animal，直接复用父类的breath()方法
 **/
class Fish extends Animal{

    public void swim(){
        System.out.println("鱼会游泳");
    }
}
/**
 * 继承Animal，直接复用父类的breath()方法
 **/
class Dog extends Animal{

    public void run(){
        System.out.println("狗会跑");
    }
}

