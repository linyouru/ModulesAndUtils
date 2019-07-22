package lyr.utils.utils;

import java.util.UUID;

/**
 *  Uid生成工具
 * @Author lyr
 * @Date 15:12 2018-11-15
 * @Param
 * @return
 **/
public class UuidUtil {

    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
