package sorter;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author climb.xu
 * @date 2022/10/11 10:10
 */
public class Test {
    private static final List<Sorter> sorters;


    static {
        sorters = getImplements(Sorter.class).stream().map(cls->{
            try {
                return cls.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.setOut(new PrintStream(System.out) {
            @Override
            public PrintStream printf(String format, Object... args) {
                if (args.length > 0 && args[0] instanceof Sorter.ConsoleColor) {
                    Sorter.ConsoleColor color = (Sorter.ConsoleColor) args[0];
                    Object[] realArgs = new Object[args.length - 1];
                    System.arraycopy(args, 1, realArgs, 0, args.length - 1);
                    return super.printf(color.prefix + format + color.suffix + "\n", realArgs);
                }
                return super.printf(format, args);
            }
        });
    }

    private static <T> List<Class<? extends T>> getImplements(Class<T> interfaceClass) {
        List<Class<? extends T>> classes = new ArrayList<>();
        //找到当前包的目录位置,将目录下的所有class文件反射得到Class,筛选出目标接口的实现类
        String packageName = interfaceClass.getPackage().getName();
        try {
            ClassLoader classLoader = interfaceClass.getClassLoader();
            String path = packageName.replace(".", "/");
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File dir = new File(resource.getFile());
                File[] files = dir.listFiles();
                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".class")) {
                         fileName = fileName.substring(0, fileName.length() - 6);
                        Class<?> aClass = Class.forName(packageName + "." + fileName);
                        if (interfaceClass.isAssignableFrom(aClass) && !interfaceClass.equals(aClass)) {
                            classes.add((Class<? extends T>) aClass);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static void main(String[] args) throws Exception {
        long[] list = {156, 290, 89, 361, 473, 38, 104, 221, 429, 245, 82, 307, 165, 280, 384, 125, 10, 292, 261, 308, 49, 179, 232, 353, 106, 483, 347, 315, 21, 157, 492, 119, 41, 187, 209, 43, 235, 250, 401, 301, 431, 111, 395, 204, 129, 134, 456, 199, 27, 53, 314, 468, 394, 191, 418, 342, 172, 422, 451, 9, 95, 222, 14, 322, 98, 261, 257, 452, 466, 171, 124, 477, 245, 293, 244, 476, 339, 150, 355, 217, 420, 262, 144, 462, 304, 144, 37, 224, 85, 470, 107, 69, 348, 441, 352, 320, 235, 19, 462, 229, 408, 182, 454, 193, 314, 199, 90, 122, 207, 348, 409, 93, 245, 261, 287, 28, 121, 173, 297, 464, 331, 460, 156, 92, 164, 248, 439, 319, 191, 260, 201, 96, 373, 334, 374, 118, 496, 94, 102, 153, 63, 424, 134, 465, 242, 135, 46, 220, 147, 193, 270, 77, 256, 330, 313, 274, 363, 372, 282, 44, 422, 195, 422, 67, 482, 148, 126, 223, 61, 152, 282, 482, 376, 235, 287, 36, 277, 99, 252, 447, 108, 19, 455, 114, 28, 311, 201, 224, 216, 383, 222, 64, 60, 261, 159, 232, 384, 493, 299, 144, 230, 387, 418, 400, 405, 495, 476, 289, 446, 299, 389, 398, 220, 119, 441, 453, 169, 462, 37, 201, 34, 59, 22, 266, 342, 21, 437, 456, 408, 287, 217, 315, 29, 123, 470, 215, 449, 114, 422, 481, 305, 59, 358, 472, 405, 308, 170, 212, 225, 428, 344, 180, 470, 234, 89, 28, 65, 365, 267, 29, 276, 148, 495, 229, 64, 25, 304, 476, 306, 55, 392, 292, 89, 133, 88, 180, 131, 165, 150, 229, 494, 108, 147, 437, 305, 47, 414, 36, 352, 242, 439, 454, 28, 358, 433, 489, 101, 373, 282, 361, 240, 175, 72, 309, 96, 116, 359, 257, 334, 111, 353, 367, 306, 387, 312, 131, 38, 368, 223, 108, 475, 239, 391, 438, 95, 83, 327, 114, 133, 466, 148, 294, 44, 221, 245, 82, 138, 174, 84, 131, 39, 271, 89, 244, 441, 152, 161, 259, 80, 161, 54, 358, 365, 293, 346, 199, 396, 84, 446, 334, 53, 297, 423, 486, 329, 179, 407, 415, 64, 85, 32, 15, 169, 426, 450, 392, 127, 422, 299, 262, 359, 320, 257, 82, 370, 438, 103, 38, 279, 30, 334, 164, 105, 432, 256, 276, 68, 208, 22, 80, 63, 265, 99, 188, 249, 389, 255, 62, 91, 163, 288, 221, 400, 369, 476, 447, 211, 264, 455, 223, 259, 358, 342, 497, 352, 235, 114, 250, 342, 204, 178, 321, 386, 354, 268, 393, 339, 71, 415, 178, 27, 113, 258, 269, 379, 345, 306, 156, 381, 317, 320, 209, 267, 17, 457, 219, 344, 400, 329, 497, 157, 106, 486, 31, 248, 290, 292, 100, 322, 448, 414, 215, 31, 83, 456, 287, 246, 173, 443, 368, 433, 281, 201, 398, 270, 145, 302, 245, 230, 53, 396, 53, 319, 109, 106, 375, 122, 380, 269};
        // long[] list = {4, 2, 6, 3, 1, 7};
        System.out.println("原数组 " + Arrays.toString(list));
        for (Sorter sorter : sorters) {
            long start = System.currentTimeMillis();
            long[] sorted = sorter.sort(list.clone());
            long end = System.currentTimeMillis();
            System.out.printf(sorter.getName() + " " + (end - start), sorter.getColor());
            System.out.printf(Arrays.toString(sorted), sorter.getColor());
        }
    }


}
