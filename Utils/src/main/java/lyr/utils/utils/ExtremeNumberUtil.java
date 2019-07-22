package lyr.utils.utils;
/**
 * 输入若干整数，返回最值
 * @Author lyr
 * @Date 10:50 2019-01-21
 * @Param
 * @return
 **/
public class ExtremeNumberUtil {

    //返回最大值
    public static int maxNumber(int... value){
        int maxNum = value[0];
        for (int i : value) {
            maxNum = i>maxNum?i:maxNum;
        }
        return maxNum;
    }

    //返回最小值
    public static int minNumber(int... value){
        int minNum = value[0];
        for (int i : value) {
            minNum = i<minNum?i:minNum;
        }
        return minNum;
    }

}
