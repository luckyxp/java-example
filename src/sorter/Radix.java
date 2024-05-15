package sorter;

import java.util.Arrays;

public class Radix implements Sorter {
    @Override
    public long[] sort(long[] source) {
        //基数排序需要得知数组的最大值有多少位
        int maxBit = (Arrays.stream(source).max().orElse(0) + "").length();
        for (int i = 1; i <= maxBit; i++) {
            long[][] barrels = new long[10][];
            for (long n : source) {
                int numByBit = getNumByBit(n, i);
                barrels[numByBit] = arrayAppend(barrels[numByBit], n);
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

        }
        return source;
    }

    private int getNumByBit(long num, int bit) {
        return (int) (num % nPower(bit) / nPower(bit - 1));
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

    private int nPower(int n) {
        StringBuilder numStr = new StringBuilder("1");
        for (int i = 0; i < n; i++) {
            numStr.append("0");
        }
        return Integer.parseInt(numStr.toString());
    }

    @Override
    public String getName() {
        return "基数排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.PURPLE;
    }
}
