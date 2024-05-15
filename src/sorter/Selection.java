package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 15:21
 */
public class Selection implements Sorter{
    @Override
    public long[] sort(long[] source) {
        for (int i = 0; i < source.length-1; i++) {
            int minIndex = i;
            for (int j = i; j < source.length; j++) {
                if (source[j] < source[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(source, i, minIndex);
            }
        }
        return source;
    }

    @Override
    public String getName() {
        return "选择排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.GREEN;
    }
}
