package sorter;

public class Heap implements Sorter {
    @Override
    public long[] sort(long[] source) {
        //构建大顶堆
        //从最后一个非叶子节点开始往上遍历
        for (int i = source.length / 2 - 1; i >= 0; i--) {
            down(source, i, source.length);
        }
        //堆顶和堆尾交换,堆尾认为不在堆内,依次类推
        for (int len = source.length - 1; len > 0; len--) {
            swap(source, 0, len);
            down(source, 0, len);
        }

        return source;
    }

    @Override
    public String getName() {
        return "堆排序";
    }

    //小节点下沉
    private void down(long[] source, int i, int len) {
        int left = 2 * i + 1;
        int right = left + 1;
        int parent = i;
        if (left < len && source[left] > source[parent]) {
            parent = left;
        }
        if (right < len && source[right] > source[parent]) {
            parent = right;
        }
        if (parent != i) {
            swap(source, i, parent);
            down(source, parent, len);
        }

    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.CYAN;
    }
}
