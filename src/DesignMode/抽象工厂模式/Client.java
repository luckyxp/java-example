package DesignMode.抽象工厂模式;

/**
 * @author climb.xu
 * @date 2022/5/9 0:04
 * 3.抽象工厂模式
 *      抽象工厂模式
 *      具体工厂
 *      抽象产品
 *      具体产品
 */
public class Client {
    public static void main(String[] args) {
        ProductA productA1 = new ProductAFactory().makeProductA(ProductFactory.PRODUCT_1);
        ProductA productA2 = new ProductAFactory().makeProductA(ProductFactory.PRODUCT_2);
        ProductB productB1 = new ProductBFactory().makeProductB(ProductFactory.PRODUCT_1);
        ProductB productB2 = new ProductBFactory().makeProductB(ProductFactory.PRODUCT_2);
        System.out.println(productA1);
        System.out.println(productA2);
        System.out.println(productB1);
        System.out.println(productB2);

    }
}
//抽象产品A
interface ProductA{ }
//具体产品A1
class ProductA1 implements ProductA{ }
//具体产品A2
class ProductA2 implements ProductA{ }
//抽象产品B
interface ProductB{ }
//具体产品B1
class ProductB1 implements ProductB{ }
//具体产品B2
class ProductB2 implements ProductB{ }
//抽象工厂模式
interface ProductFactory{
    public static final int PRODUCT_1 = 1;
    public static final int PRODUCT_2 = 2;
    ProductA makeProductA(int kind);
    ProductB makeProductB(int kind);
}
//具体工厂A,用来生成产品A1和A2
class ProductAFactory implements ProductFactory{

    @Override
    public ProductA makeProductA(int kind) {
        return kind == PRODUCT_1 ? new ProductA1() : new ProductA2();
    }

    @Override
    public ProductB makeProductB(int kind) {return null;}
}
//具体工厂B,用来生成产品B1和B2
class ProductBFactory implements ProductFactory{

    @Override
    public ProductA makeProductA(int kind) {return null;}

    @Override
    public ProductB makeProductB(int kind) {
        return kind == PRODUCT_1 ? new ProductB1() : new ProductB2();
    }
}
