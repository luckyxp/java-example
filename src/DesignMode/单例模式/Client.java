package DesignMode.单例模式;

/**
 * @author climb.xu
 * @date 2022/5/9 10:39
 * 6.单例模式
 *      单例类
 */
public class Client {
    public static void main(String[] args) {
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        Singleton2 instance3 = Singleton2.getInstance();
        Singleton2 instance4 = Singleton2.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
    }
}
//饿汉式
class Singleton1{
    private static final Singleton1 instance = new Singleton1();
    private Singleton1() {

    }
    public static Singleton1 getInstance() {
        return instance;
    }
}
//懒汉式
class Singleton2{
    private static  Singleton2 instance;
    private Singleton2() {

    }
    public static Singleton2 getInstance() {
        return instance == null ? instance = new Singleton2() : instance;
    }
}