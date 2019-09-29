package lyr.learn.pattern.decorator;

/**
 * 装饰者模式,指在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）的模式，它属于对象结构型模式。
 * 优点：
 * 采用装饰模式扩展对象的功能比采用继承方式更加灵活。
 * 可以设计出多个不同的具体装饰类，创造出多个不同行为的组合。
 *
 * 缺点：装饰模式增加了许多子类，如果过度使用会使程序变得很复杂。
 * @ClassName Decorator
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/24 15:05
 * @Version 1.0
 **/
public class Decorator {
    public static void main(String[] args) {
        //创建一种叫咖啡的饮料
        Beverage coffee = new Coffee();
        //给咖啡加糖
        coffee = new SugarCoffee(coffee);
        //给咖啡加牛奶
        coffee = new MilkCoffee(coffee);
        //给咖啡加柠檬
        coffee = new LemonCoffee(coffee);

        System.out.println("你点的饮料是："+coffee.getDescription()+"\n"+"价格是："+coffee.getPrice()+"元");
    }
}

/**
 * 饮料抽象类
 * 定义一个抽象接口以规范准备接收附加责任的对象。
 */
abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double getPrice();
}

/**
 * 咖啡饮料
 * 实现抽象构件，通过装饰角色为其添加一些职责。
 */
class Coffee extends Beverage {

    public Coffee() {
        description = "咖啡饮料";
    }

    @Override
    public double getPrice() {
        return 10.00;
    }
}

/**
 * 饮料装饰者抽象类
 * 继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 */
abstract class BeverageDecorator extends Beverage {
    private Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }

    @Override
    public double getPrice() {
        return beverage.getPrice();
    }
}

/**
 * 装饰者类，负责给咖啡加糖
 * 实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 */
class SugarCoffee extends BeverageDecorator {

    public SugarCoffee(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，加糖";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2.00;
    }
}

/**
 * 装饰者类，负责给咖啡加牛奶
 */
class MilkCoffee extends BeverageDecorator {

    public MilkCoffee(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，加牛奶";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 3.00;
    }
}

/**
 * 装饰者类，负责给咖啡加柠檬
 */
class LemonCoffee extends BeverageDecorator {

    public LemonCoffee(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，加柠檬";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 4.00;
    }
}