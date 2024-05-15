import java.util.ArrayList;
import java.util.List;

/**
 * @author climb.xu
 * @date 2022/12/12 15:25
 */
public class TestLoopRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("五");
        // for (String l : list) {
        //     list.remove(l);
        // }
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list);
    }
}
