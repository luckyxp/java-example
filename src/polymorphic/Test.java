package polymorphic;

/**
 * @author climb.xu
 * @date 2022/4/22 10:49
 */

class Son extends Father {
    int x = 30;

    public Son() {
        this.print();
        this.x = 40;
    }
    @Override
    public void print() {
        System.out.println("Son.x=" + this.x);
    }
}
class Father{
    int x = 10;

    public Father() {
        this.print();
        this.x = 20;
    }

    public void print() {
        System.out.println("Father.x=" + this.x);
    }
}
public class Test {
    public static void main(String[] args) {
        Father father = new Son();
        System.out.println(father.x);
    }
}