package reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author climb.xu
 * @date 2022/3/14 15:14
 * 相关类,Constructor构造器,Field属性,Method方法
 */
public class Test {
    public static void main(String[] args) {
        Car car1 = new Car("ss");
        //Monkey car2 = new Monkey();
        try {
            Class<?> aClass = Class.forName("reflex.Car");
            Car car3 = (Car)aClass.newInstance();//Class直接newInstance只能调用空参构造,且不能访问private
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> aClass = Class.forName("reflex.Car");
//            Constructor<?> constructor = aClass.getConstructor(String.class);//getConstructor只能获取public的构造器
            Constructor<?> constructor = aClass.getDeclaredConstructor();//getDeclaredConstructor可以获取所有修饰的构造器,但是非public的构造器需要setAccessible(true)
            constructor.setAccessible(true);
            Car car4 = (Car)constructor.newInstance();
            System.out.println(car4);


//            Field name = aClass.getField("name");//getField类似,只能获取public属性
            Field name = aClass.getDeclaredField("name");//同样,访问非public属性时也需要setAccessible(true)
            name.setAccessible(true);
            name.set(car4,"李四");
            System.out.println(car4);


//            Method getName = aClass.getMethod("getName");//类似
            Method getName = aClass.getDeclaredMethod("getName");//类似
            getName.setAccessible(true);//类似
            System.out.println(getName.invoke(car4));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
