package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 15:37
 */
public class Shell implements Sorter {
    @Override
    public long[] sort(long[] source) {
        for (int s = source.length / 2; s > 0; s /= 2) {//控制步长
            for (int i = s; i < source.length; i++) {//控制当前排序的是第几组 i为当前组的第二个元素
                for (int j = i; j < source.length; j += s) {//控制组内,开始对第几个元素开始选择排序
                    for (int k = j; k >= s; k -= s) {//控制选择到了第几个元素
                        if (source[k] < source[k - s]) {
                            swap(source, k, k - s);
                        } else {
                            break;
                        }
                    }
                }

            }

        }
        return source;
    }

    @Override
    public String getName() {
        return "希尔排序";
    }

    @Override
    public ConsoleColor getColor() {
        return ConsoleColor.BLUE;
    }
}
