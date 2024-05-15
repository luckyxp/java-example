package DesignMode.桥接模式;

/**
 * @author climb.xu
 * @date 2022/5/9 11:04
 * 8.桥接模式
 *      抽象类
 *      扩充抽象类
 *      实现类接口
 *      具体实现类
 */
public class Client {
    public static void main(String[] args) {
        Implementor c1 = new ConcreteImplementor1();
        Implementor c2 = new ConcreteImplementor2();
        Abstraction r1 = new RefinedAbstraction1(c1);
        Abstraction r2 = new RefinedAbstraction1(c2);
        Abstraction r3 = new RefinedAbstraction2(c1);
        Abstraction r4 = new RefinedAbstraction2(c2);
        r1.operationImpl();
        r2.operationImpl();
        r3.operationImpl();
        r4.operationImpl();
    }
}

//实现类接口
interface Implementor {
    void operation(String str);
}

//具体实现类1
class ConcreteImplementor1 implements Implementor{
    @Override
    public void operation(String str) {
        System.out.println("op1"+str);
    }
}
//具体实现类2
class ConcreteImplementor2 implements Implementor{
    @Override
    public void operation(String str) {
        System.out.println("op2"+str);
    }
}
//抽象类
abstract class Abstraction{
    protected Implementor implementor;
    public abstract void operationImpl();
}

//扩充抽象类1
class RefinedAbstraction1 extends Abstraction{
    public RefinedAbstraction1(Implementor implementor) {
        super.implementor = implementor;
    }
    @Override
    public void operationImpl() {
        implementor.operation("refined1");
    }
}
//扩充抽象类2
class RefinedAbstraction2 extends Abstraction{
    public RefinedAbstraction2(Implementor implementor) {
        super.implementor = implementor;
    }
    @Override
    public void operationImpl() {
        implementor.operation("refined2");
    }
}