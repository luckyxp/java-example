package DesignMode.工厂方法模式;

/**
 * @author climb.xu
 * @date 2022/5/8 23:45
 * 2.工厂方法模式
 *      抽象产品
 *      具体产品
 *      抽象工厂模式
 *      具体工厂
 * 扩展:抽象工厂角色种可以定义多个工厂方法,让具体工厂角色实现这些不同的工厂方法
 */
public class Client {
    public static void main(String[] args) {
        Product product1 = new AFactory().makeProduct();
        System.out.println(product1);
        Product product2 = new BFactory().makeProduct();
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
//抽象工厂模式
interface ProductFactory {
    Product makeProduct();
}
//用来生成A产品的具体工厂
class AFactory implements ProductFactory{
    @Override
    public Product makeProduct() {
        return new ProductA();
    }
}
//用来生成B产品的具体工厂
class BFactory implements ProductFactory{
    @Override
    public Product makeProduct() {
        return new ProductB();
    }
}