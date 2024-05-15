package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 15:38
 */
public class Merge implements Sorter {
    @Override
    public long[] sort(long[] source) {
        sort(source, 0, source.length - 1);
        return source;
    }

    @Override
    public String getName() {
        return "归并排序";
    }

    public void sort(long[] source, int start, int end) {
        if (end != start) {
            int mid = start + (end - start) / 2;
            sort(source, start, mid);
            sort(source, mid + 1, end);
            merge(source, start, mid, end);
        }
    }


    public void merge(long[] source, int start, int mid, int end) {
        long[] temp = new long[end - start + 1];
        int l = start;
        int r = mid + 1;
        for (int i = 0; i < temp.length; i++) {
            int min;
            if (l < mid + 1) {
                if (r <= end && source[r] < source[l]) {
                    min = r++;
                } else {
                    min = l++;
                }
            } else {
                min = r++;
            }
            temp[i] = source[min];
        }
        for (int i = 0; i < temp.length; i++) {
            source[start + i] = temp[i];
        }
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.PURPLE;
    }
}
