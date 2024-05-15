package DesignMode.原型模式;

/**
 * @author climb.xu
 * @date 2022/5/9 0:46
 * 5.原型模式
 *      抽象原型类
 *      具体原型类
 *      客户类
 */
public class Client {
    public static void main(String[] args) {
        Product product = new Product();
        Product cpProduct = (Product) product.clone();
        System.out.println(product);
        System.out.println(product.getAttr());
        System.out.println(cpProduct);
        System.out.println(cpProduct.getAttr());
    }
}
//抽象原型类 Object

//具体原型类
class Product implements Cloneable{
    private Attr attr;
    class Attr{
        String name;
        double price;
        public Attr(String name,double price){
            this.name = name;
            this.price = price;
        }
    }

    public Attr getAttr() {
        return attr;
    }

    public Product() {
        this.attr = new Attr("p1", 10);
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();//浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

