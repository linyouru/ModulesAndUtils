package lyr.learn.pattern.Factory;

/**
 * 工厂模式
 * 优点：
 * 用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
 * 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
 * 缺点：
 * 每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。
 * @ClassName Factory
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/23 16:41
 * @Version 1.0
 **/
public class Factory {

    public static void main(String[] args) {
        ConcreteFactory1 concreteFactory1 = new ConcreteFactory1();
        ConcreteFactory2 concreteFactory2 = new ConcreteFactory2();
        Product product1 = concreteFactory1.newProduct();
        Product product2 = concreteFactory2.newProduct();
        product1.show();
        product2.show();

    }
}

//抽象产品
interface Product{
    void show();
}

//具体产品1
class ConcreteProduct1 implements Product{

    @Override
    public void show() {
        System.out.println("显示具体产品1");
    }
}

//具体产品2
class ConcreteProduct2 implements Product{

    @Override
    public void show() {
        System.out.println("显示具体产品2 ");
    }
}

//抽象工厂
interface AbstractFactory1{
    Product newProduct();
}

//具体工厂1
class ConcreteFactory1 implements AbstractFactory1{
    @Override
    public Product newProduct() {
        System.out.println("具体工厂1-->具体产品1生产");
        return new ConcreteProduct1();
    }
}

//具体工厂2
class ConcreteFactory2 implements AbstractFactory1{

    @Override
    public Product newProduct() {
        System.out.println("具体工厂2-->具体产品2生产");
        return new ConcreteProduct2();
    }
}