/**
 * @author climb.xu
 * @date 2022/6/20 20:02
 */
public class Test {

    private static char[] fullCharSource = { '1','2','3','4','5','6','7','8','9','0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',  'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    //将可能的密码集合长度
    private static int fullCharLength = fullCharSource.length;
    /**
     * 穷举打印输出，可以将打印输出的文件形成字典
     * @param maxLength：生成的字符串的最大长度
     */
    public static void generate(int maxLength) {
        //计数器，多线程时可以对其加锁，当然得先转换成Integer类型。
        int counter = 0;
        StringBuilder buider = new StringBuilder();
        while (buider.toString().length() <= maxLength) {
            buider = new StringBuilder(maxLength*2);
            int _counter = counter;
            //10进制转换成26进制
            while (_counter >= fullCharLength) {
                //获得低位
                buider.insert(0, fullCharSource[_counter % fullCharLength]);
                _counter = _counter / fullCharLength;
                //精髓所在，处理进制体系中只有10没有01的问题，在穷举里面是可以存在01的
                _counter--;
            }
            //最高位
            buider.insert(0,fullCharSource[_counter]);
            counter++;
            System.out.println(buider.toString());
        }
    }
    public static void main(String[] args) {
        Integer num7 = Integer.valueOf(-128);
        Integer num8 = Integer.valueOf(-128);
        System.out.println(num7 == num8);
    }

}
