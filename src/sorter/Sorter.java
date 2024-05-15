package sorter;

/**
 * @author climb.xu
 * @date 2022/10/11 10:11
 */
public interface Sorter {
    long[] sort(long[] source);

    String getName();

    ConsoleColor getColor();

    default void swap(long[] arr, int index1, int index2) {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    enum ConsoleColor {
        RED("\033[1m\033[31m"),
        GREEN("\033[1m\033[32m"),
        YELLOW("\033[1m\033[33m"),
        BLUE("\033[1m\033[34m"),
        PURPLE("\033[1m\033[35m"),
        CYAN("\033[1m\033[36m"),
        GREY("\033[1m\033[37m");

        ConsoleColor(String prefix) {
            this.prefix = prefix;
        }

        public final String prefix;
        public final String suffix = "\033[0m";
    }
}
