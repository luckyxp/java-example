package polymorphic;

/**
 * @author climb.xu
 * @date 2022/4/22 22:24
 */
public strictfp class Demo {
    public static final transient int num = 1;
    public int add() {
//        num = num + 2;
        return num;
    }

    public static void main(String[] args) {
       
    }


}
