package reflex;

/**
 * @author climb.xu
 * @date 2022/3/14 15:13
 */
public class Car {
    private String name;

    private Car() {

    }

    public Car(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                '}';
    }
}
