package lyr.learn.pattern.singleton;

/**
 * @ClassName SingletonTest
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/3 14:18
 * @Version 1.0
 **/
public class SingletonTest {

    public static void main(String[] args) {

        SingletonTest test = new SingletonTest();
        test.enumSingleton();

    }


    void hungrySingleton(){

        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();

        if(hungrySingleton1==hungrySingleton2){
            System.out.println("true");
        }

    }

    void enumSingleton(){
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance1==instance2);
    }

}
