package sorter;

import java.util.Arrays;

public class Count implements Sorter {
    @Override
    public long[] sort(long[] source) {
        //计数排序需要得知数组的取值范围 计数排序是 桶大小为1的桶排序
        long min = Arrays.stream(source).min().orElse(0);
        long max = Arrays.stream(source).max().orElse(0);
        long[] table = new long[(int) (max - min) + 1];
        for (long n : source) {
            table[(int) (n - min)]++;
        }
        int p = 0;
        for (int i = 0; i < source.length; i++) {
            while (p < table.length) {
                if (table[p] != 0) {
                    source[i] = p + min;
                    table[p]--;
                    break;
                } else {
                    p++;
                }
            }
        }
        return source;
    }

    @Override
    public String getName() {
        return "计数排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.GREY;
    }
}
