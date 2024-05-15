package sorter;

public class DualPivotQuick implements Sorter {
    @Override
    public long[] sort(long[] source) {
        part(source, 0, source.length - 1);
        return source;
    }

    @Override
    public String getName() {
        return "双轴快排";
    }

    private void part(long[] source, int start, int end) {
        if (start >= end) {
            return;
        }
        //选取start和end作为左轴和右轴,start值大于end值,就交换他们的值
        if (source[start] > source[end]) {
            swap(source, start, end);
        }
        long lPivotV = source[start];
        long rPivotV = source[end];

        int l = start + 1;
        int p = l;
        int r = end - 1;

        while (p <= r) {
            if (source[p] < lPivotV) {
                //左边区域
                swap(source, p, l);
                l++;
                p++;
            } else if (source[p] > rPivotV) {
                //右边区域
                swap(source, p, r);
                r--;
            } else {
                //中间区域
                p++;
            }
        }
        swap(source, l - 1, start);
        swap(source, r + 1, end);

        part(source, start, l - 1);
        part(source, l + 1, r - 1);
        part(source, r + 1, end);

    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.BLUE;
    }
}
