package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 15:36
 */
public class Insertion implements Sorter {
    @Override
    public long[] sort(long[] source) {
        for (int i = 1; i < source.length; i++) {
            for (int j = i; j > 0; j--) {
                if (source[j] < source[j - 1]) {
                    swap(source, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return source;
    }

    @Override
    public String getName() {
        return "插入排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.YELLOW;
    }
}
