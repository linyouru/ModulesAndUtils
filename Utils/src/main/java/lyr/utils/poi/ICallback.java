package lyr.utils.poi;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @ClassName ICallback
 * @Description TODO
 * @Author LYR
 * @Date 2020/3/11 17:30
 * @Version 1.0
 **/
public interface ICallback {

    void todo(List<T> data);

    Class getTClass();
}
