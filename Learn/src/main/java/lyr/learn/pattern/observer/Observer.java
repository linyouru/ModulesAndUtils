package lyr.learn.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * @ClassName Observer
 * @Description TODO
 * @Author LYR
 * @Date 2019/9/24 16:32
 * @Version 1.0
 **/
public class Observer {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        AbstractObserver observer1 = new ConcreteObserver1();
        AbstractObserver observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();
    }
}

//抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
abstract class Subject{
    protected List<AbstractObserver> observers=new ArrayList<AbstractObserver>();
    //增加观察者方法
    public void add(AbstractObserver observer)
    {
        observers.add(observer);
    }
    //删除观察者方法
    public void remove(AbstractObserver observer)
    {
        observers.remove(observer);
    }
    public abstract void notifyObserver(); //通知观察者方法
}

//具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
class ConcreteSubject extends Subject{

    @Override
    public void notifyObserver() {
        System.out.println("具体目标改变");
        for (AbstractObserver observer : observers) {
            observer.response();
        }
    }
}

//抽象观察者，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
interface AbstractObserver {
    //反应
    void response();
}

//具体观察者，实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
class ConcreteObserver1 implements AbstractObserver {

    @Override
    public void response() {
        System.out.println("具体观察者1做出反应");
    }
}

class ConcreteObserver2 implements AbstractObserver {

    @Override
    public void response() {
        System.out.println("具体观察者2做出反应");
    }
}