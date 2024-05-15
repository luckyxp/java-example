package proxy;

/**
 * @author climb.xu
 * @date 2022/3/14 15:43
 */
public class Man implements Person {
    public int i = 1;
    @Override
    public boolean sleep(long time) {
        System.out.println("睡"+time);
        return true;
    }
    @Override
    public boolean eat(String food) {
        System.out.println("吃"+food);
        return false;
    }


}
