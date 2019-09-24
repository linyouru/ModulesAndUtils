package lyr.learn.pattern.Factory;

/**
 * 抽象工厂
 *使用抽象工厂模式一般要满足以下条件。
 * 系统中有多个产品族，每个具体工厂创建同一族但属于不同等级结构的产品。
 * 系统一次只可能消费其中某一族产品，即同族的产品一起使用。
 *
 * 优点:
 * 可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 *
 * 缺点：
 * 当产品族中需要增加一个新种类的产品时，则所有的工厂类都需要进行修改
 * @ClassName AbstractFactory
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/24 9:27
 * @Version 1.0
 **/
public class AbstractFactory {
    public static void main(String[] args) {
        XiaomiProductFactory xiaomiProductFactory = new XiaomiProductFactory();
        HuaweiProductFactory huaweiProductFactory = new HuaweiProductFactory();
        Phone xiaomiPhone = xiaomiProductFactory.produceTelPhone();
        Router xiaomiRouter = xiaomiProductFactory.produceRouter();
        Phone huaweiPhone = huaweiProductFactory.produceTelPhone();
        Router huaweiRouter = huaweiProductFactory.produceRouter();
        xiaomiPhone.callUp();
        xiaomiRouter.openWifi();
        huaweiPhone.callUp();
        huaweiRouter.openWifi();
    }
}

//手机产品接口
interface Phone {
    /**
     * 拨打电话
     */
    void callUp();
}
//小米手机
class XiaomiPhone  implements Phone{

    @Override
    public void callUp() {
        System.out.println("小米手机-->callUp");
    }
}
//华为手机
class HuaweiPhone implements Phone{

    @Override
    public void callUp() {
        System.out.println("华为手机-->callUp");
    }
}
//路由器产品接口
interface Router {
    /**
     * 开启wifi
     */
    void openWifi();
}
//小米路由器
class XiaomiRouter implements Router{

    @Override
    public void openWifi() {
        System.out.println("小米路由器-->openWifi");
    }
}
//华为路由器
class HuaweiRouter implements Router{

    @Override
    public void openWifi() {
        System.out.println("华为路由器-->openWifi");
    }
}
//抽象工厂
interface ProductFactory{
    Phone produceTelPhone();
    Router produceRouter();
}
//小米工厂
class XiaomiProductFactory  implements ProductFactory{

    @Override
    public Phone produceTelPhone() {
        return new XiaomiPhone();
    }

    @Override
    public Router produceRouter() {
        return new XiaomiRouter();
    }
}
//华为工厂
class HuaweiProductFactory  implements ProductFactory{

    @Override
    public Phone produceTelPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Router produceRouter() {
        return new HuaweiRouter();
    }
}
