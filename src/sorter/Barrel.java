package sorter;

import java.util.Arrays;

public class Barrel implements Sorter {
    @Override
    public long[] sort(long[] source) {
        //初始化桶大小
        int barrelSize = 2;
        //桶排序需要得知数组的取值范围
        long min = Arrays.stream(source).min().orElse(0);
        long max = Arrays.stream(source).max().orElse(0);
        int barrelNum = (int) ((max - min) / barrelSize + 1);
        long[][] barrels = new long[barrelNum][];
        for (long n : source) {
            int i = (int) ((n - min) / barrelSize);
            barrels[i] = arrayAppend(barrels[i], n);
        }
        int p = 0;
        for (long[] barrel : barrels) {
            if (barrel != null) {
                Arrays.sort(barrel);
                for (long n : barrel) {
                    source[p] = n;
                    p++;
                }
            }
        }
        return source;
    }
    private long[] arrayAppend(long[] arr, long n) {
        if (arr == null) {
            return new long[]{n};
        }
        long[] newArr = new long[arr.length + 1];
        newArr[newArr.length - 1] = n;
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    @Override
    public String getName() {
        return "桶排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.CYAN;
    }
}
