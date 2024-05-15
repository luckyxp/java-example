package proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author climb.xu
 * @date 2022/3/14 15:42
 */
public class Test {

    public static class SimpleMethodInterceptor implements MethodInterceptor {
        private final Monkey monkey = new Monkey();
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return method.invoke(monkey, objects);
        }
    }

    public static void main(String[] args) {
        //Objenesis+cglib代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Monkey.class);
        enhancer.setCallbackType(SimpleMethodInterceptor.class);
        Class proxyClass = enhancer.createClass();
        Enhancer.registerCallbacks(proxyClass, new Callback[]{new SimpleMethodInterceptor()});

        ObjenesisStd objenesis = new ObjenesisStd();
        Monkey monkeyProxy = (Monkey) objenesis.newInstance(proxyClass);
        monkeyProxy.dump(1000);
        //name为null
        System.out.println(monkeyProxy.name);
        //name不为null
        System.out.println(monkeyProxy.getName());
    }

    public static void main2(String[] args) {
        //cglib代理
        Monkey monkeyProxy = (Monkey) Enhancer.create(Monkey.class, new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("1");
                Object result = method.invoke(new Monkey(), objects);
                System.out.println("2");
                return result;
            }
        });

        //再次代理
        Monkey monkeyReProxy = (Monkey) Enhancer.create(Monkey.class, new net.sf.cglib.proxy.InvocationHandler() {//一定不能是monkeyProxy.getClass(),因为getClass是获取运行时的类,此时获取到的是代理类,而不是真正的Monkey类
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(monkeyProxy, objects);
                System.out.println("after");
                return result;
            }
        });
//        monkeyProxy.dump(1000);
        monkeyReProxy.dump(1000);
        System.out.println(monkeyProxy.getClass());
    }

    public static void main1(String[] args) {
        //jdk动态代理,必须通过需要代理的类的接口
        Person manProxy1 = (Person) Proxy.newProxyInstance(Man.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//会代理所有方法,即所有方法的调用都会走这里
                if ("eat".equals(method.getName())) {//代理指定方法
                    System.out.println("before");
                }
                Object result = method.invoke(new Man(), args);//此处千万不要用proxy,而是要new一个需要代理的对象,因为此处proxy不是目标对象
                if ("eat".equals(method.getName())) {
                    System.out.println("after");
                }
                return result;
            }
        });
        System.out.println(manProxy1.eat("橘子"));
        System.out.println(manProxy1.sleep(100));
        System.out.println(manProxy1.getClass());//即生成了一个实现了Person类的类的实例作为代理
        //另一种写法,即需要代理的目标对象已经提前new好了
        Man man = new Man();
        Person manProxy2 = (Person) Proxy.newProxyInstance(man.getClass().getClassLoader(), man.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(man, args);//此处千万不要用proxy,而是要new一个需要代理的对象,因为此处proxy不是目标对象
                System.out.println("after");
                return result;
            }
        });
        System.out.println(manProxy2.eat("橙子"));
        System.out.println(manProxy2);//此处会调用toString方法,toString方法也会被代理
    }
}
