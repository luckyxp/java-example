package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 10:09
 */
public class Bubble implements Sorter {

    @Override
    public long[] sort(long[] source) {
        for (int i = source.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (source[j] > source[j + 1]) {
                    swap(source, j, j + 1);
                }
            }
        }
        return source;
    }

    @Override
    public String getName() {
        return "冒泡排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.RED;
    }

}
