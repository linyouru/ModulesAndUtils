package lyr.learn.pattern.builder;

/**
 * 建造者模式
 * 优点：
 * 各个具体的建造者相互独立，有利于系统的扩展。
 * 客户端不必知道产品内部组成的细节，便于控制细节风险。
 *
 * 缺点：
 * 产品的组成部分必须相同，这限制了其使用范围。
 * 如果产品的内部变化复杂，该模式会增加很多的建造者类。
 *
 * 建造者（Builder）模式和工厂模式的关注点不同：建造者模式注重零部件的组装过程，而工厂方法模式更注重零部件的创建过程，但两者可以结合使用。
 * @ClassName Builder
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/24 11:26
 * @Version 1.0
 **/
public class Builder {

    public static void main(String[] args) {
        ConcreteBuilder1 builder1 = new ConcreteBuilder1();
        Director director = new Director(builder1);
        Product construct = director.construct();
        System.out.println("产品构成：部件1["+construct.getPart1()+"];部件2["+construct.getPart2()+"];部件3["+construct.getPart3()+"]");
    }

}

//产品
class Product {
    private String part1;
    private String part2;
    private String part3;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }
}
//抽象建造者,定义了产品的创建方法和返回方法
abstract class AbstractBuilder{
    Product product = new Product();

    public abstract void buildPart1();
    public abstract void buildPart2();
    public abstract void buildPart3();

    public Product getProduct(){
        return product;
    }
}
//具体建造者,实现抽象接口，构建和装配各个部件
class ConcreteBuilder1 extends AbstractBuilder{

    @Override
    public void buildPart1() {
        product.setPart1("构造部件1-->类型A");
    }

    @Override
    public void buildPart2() {
        product.setPart2("构造部件2-->类型A");
    }

    @Override
    public void buildPart3() {
        product.setPart3("构造部件3-->类型A");
    }
}

class ConcreteBuilder2 extends AbstractBuilder{

    @Override
    public void buildPart1() {
        product.setPart1("构造部件1-->类型B");
    }

    @Override
    public void buildPart2() {
        product.setPart1("构造部件2-->类型B");
    }

    @Override
    public void buildPart3() {
        product.setPart1("构造部件3-->类型B");
    }
}

//建造者模式的结构中还引入了一个指挥者类Director，该类的作用主要有两个：一方面它隔离了客户与生产过程；另一方面它负责控制产品的生成过程。
// 指挥者针对抽象建造者编程，客户端只需要知道具体建造者的类型，即可通过指挥者类调用建造者的相关方法，返回一个完整的产品对象。
class Director{
    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public Product construct(){
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
        return builder.getProduct();
    }
}

