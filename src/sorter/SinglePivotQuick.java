package sorter;

public class SinglePivotQuick implements Sorter {
    @Override
    public long[] sort(long[] source) {
        part(source, 0, source.length - 1);
        return source;
    }

    public void part(long[] source, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = left;
        long pivotV = source[pivot];
        while (left < right) {
            while (left < right && source[right] >= pivotV) {
                right--;
            }
            if (left < right) {
                source[pivot] = source[right];
                pivot = right;
                left++;
            }
            while (left < right && source[left] <= pivotV) {
                left++;
            }
            if (left < right) {
                source[pivot] = source[left];
                pivot = left;
                right--;
            }
        }
        source[pivot] = pivotV;
        part(source, start, pivot - 1);
        part(source, pivot + 1, end);
    }

    @Override
    public String getName() {
        return "单轴快排";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.GREEN;
    }
}
