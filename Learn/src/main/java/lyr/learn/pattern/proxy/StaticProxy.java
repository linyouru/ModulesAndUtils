package lyr.learn.pattern.proxy;

/**
 * 静态代理
 * 缺点：真实主题与代理主题一一对应，增加真实主题也要增加代理。设计代理以前真实主题必须事先存在，不太灵活
 * @ClassName StaticProxy
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/23 15:28
 * @Version 1.0
 **/
public class StaticProxy {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}

//抽象主题
interface  Subject{
    void Request();
}

//真实主题
class RealSubject implements Subject{

    @Override
    public void Request() {
        System.out.println("执行了真实主题方法");
    }
}


//代理
class Proxy implements Subject{

    private RealSubject realSubject;

    @Override
    public void Request() {
        if(realSubject==null){
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest(){
        System.out.println("执行了真实主题之前的预处理");
    }

    public void postRequest(){
        System.out.println("执行了真实主题之后的后续处理");
    }

}


