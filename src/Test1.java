import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author climb.xu
 * @date 2022/6/29 15:14
 */
public class Test1 {
    private static Map<Long, Long> dateMap = new TreeMap<>();
    public static void dateHandle(long startTime, long endTime){
        if(startTime>endTime) return;
        if (dateMap.size()==0) {
            dateMap.put(startTime, endTime);
            return;
        }
        //TreeMap能自动根据Key排序，只需要合并交集
        //1、有交集则合并
        //2、没有交集则添加
        Iterator<Map.Entry<Long,Long>> iterator = dateMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Long,Long> entry = iterator.next();
            Long start = entry.getKey();
            Long end = entry.getValue();
            if(startTime==start && endTime<=end) {
                iterator.remove();
                dateMap.put(startTime, end);
            } else if(startTime<=start && endTime>=end) {
                iterator.remove();
                dateMap.put(startTime, endTime);
            } else if(endTime>end && startTime>=start && startTime<=end) {
                iterator.remove();
                dateMap.put(start, endTime);
            } else if(endTime>end && startTime>=end ) {
                //确定集合已经遍历完
                if(!iterator.hasNext())
                    dateMap.put(startTime, endTime);
            }
        }
    }
}
