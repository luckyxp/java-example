package DesignMode.适配器模式;

/**
 * @author climb.xu
 * @date 2022/5/9 10:47
 * 7.适配器模式
 *      目标抽象类
 *      适配器类
 *      适配者类
 *      客户类
 * 扩展:
 *      缺省适配器模式: 当需要实现一个接口,但又不想实现其所有方法,可定义一个抽象类,实现该接口,但是所有方法都不做具体实现,只返回null,子类就可以选择性实现
 *      双向适配器模式: 即目标类和是适配者类都是接口,它们相互适配,那么适配器类需实现这两个接口,对它们进行相互适配
 */
public class Client {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.fun1();
        adapter.fun2();
    }
}
//目标抽象类
interface Target{
    void f1();
    void f2();
}
//适配者类
class Adaptee{
    void fun1() {
        System.out.println("fun1");
    }

    void fun2() {
        System.out.println("fun2");
    }
}

//适配器类
class Adapter extends Adaptee implements Target {

    @Override
    public void f1() {
        super.fun1();
    }

    @Override
    public void f2() {
        super.fun2();
    }
}