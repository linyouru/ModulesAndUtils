package lyr.utils.utils;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量存储数据工具类
 * @Author lyr
 * @Date 14:40 2018-11-08
 * @Param
 * @return
 **/
public class BatchSaveDataUtil {

    /**
     * 通过JDBC,将数据分批存入Mysql
     * 创建人：lyr
     * @param data
     * @param sql
     * 创建日期：2018年6月1日上午10:44:08
     */
    public static void saveDataByJDBC(List<Object> data, String sql) {
        //将数据存入表中
        List<Object> pageData = null;
        int totalSize  = data.size();	//总数据量
        int pageSize = 10000;			//每批条数
        int totalPage = totalSize/pageSize;	//循环次数

        if (totalSize % pageSize != 0) {
            totalPage += 1;
            if (totalSize < pageSize) {
                pageSize = data.size();
            }
        }
        for (int i = 1; i <totalPage+1; i++) {
            int starNum = (i-1)*pageSize;
            int endNum = i*pageSize>totalSize?(totalSize):i*pageSize;
            pageData = data.subList(starNum,endNum);
//            getJdbcTemplate().batchUpdate(sql, pageData);
        }
    }

    /**
     * 分割数据,配合mybatis批量入库数据
     * @Author lyr
     * @Date 11:10 2018-10-31
     * @Param []
     * @return void
     **/
    public static List<List<T>> partitionData(List<T> data){
        List<List<T>> dataPackage = new ArrayList<>();
        List<T> pageData;
        int totalSize  = data.size();	//总数据量
        int pageSize = 10000;			//每批条数
        int totalPage = totalSize/pageSize;	//循环次数

        if (totalSize % pageSize != 0) {
            totalPage += 1;
            if (totalSize < pageSize) {
                pageSize = data.size();
            }
        }
        for (int i = 1; i <totalPage+1; i++) {
            int starNum = (i-1)*pageSize;
            int endNum = i*pageSize>totalSize?(totalSize):i*pageSize;
            pageData = data.subList(starNum,endNum);
            dataPackage.add(pageData);
        }
        return dataPackage;
    }

}
