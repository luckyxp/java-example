package DesignMode.简单工厂模式;

/**
 * @author climb.xu
 * @date 2022/5/8 23:36
 * 1.简单工程模式
 *      工厂角色
 *      抽象产品角色
 *      具体产品角色
 * 扩展: 工厂类可以由抽象产品角色扮演
 */
public class Client {
    public static void main(String[] args) {
        Product product1 = SimpleFactory.makeProduct(SimpleFactory.PRODUCT_A);
        System.out.println(product1);
        Product product2 = SimpleFactory.makeProduct(SimpleFactory.PRODUCT_B);
        System.out.println(product2);
    }
}
//抽象产品
interface Product{

}
//具体产品A
class ProductA implements Product{

}
//具体产品B
class ProductB implements Product{

}
//简单工厂模式
class SimpleFactory{
    public static final int PRODUCT_A = 0;
    public static final int PRODUCT_B = 1;
    public static Product makeProduct(int kind) {
        if (kind == PRODUCT_A) {
            return new ProductA();
        }else {
            return new ProductB();
        }
    }
}