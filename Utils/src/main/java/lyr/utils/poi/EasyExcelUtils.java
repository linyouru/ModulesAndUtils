package lyr.utils.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lyr.utils.poi.convert.LocalDateStringConvert;
import lyr.utils.poi.convert.LocalDateTimeStringConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


/**
 * EasyExcel工具类
 * @Author LinYouRu
 * @Date 11:52 2020/3/10
 * @return 
 **/
public class EasyExcelUtils {
    
    private static Logger logger = LoggerFactory.getLogger(EasyExcelUtils.class);

    /**
     * 导出excel
     *
     * @param response
     * @param data
     * @param tableName
     * @param cls
     */
    public static void export(HttpServletResponse response, List data, String tableName, Class cls) {
        try {
            response.setContentType("application/vnd.ms-easyExcel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + tableName + ".xlsx");
            String fileName = tableName;
            // 如果这里想使用03 则 传入excelType参数即可
            ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
            excelWriterBuilder.file(response.getOutputStream());
            if (cls != null) {
                excelWriterBuilder.head(cls);
            }
            // 由于 Java 8 localDate 和 localDateTime 不支持转化，需要添加自定义转化器
            excelWriterBuilder.registerConverter(new LocalDateStringConvert());
            excelWriterBuilder.registerConverter(new LocalDateTimeStringConvert());
            // 不设置表头和内容样式
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(null, (WriteCellStyle) null);
            excelWriterBuilder.registerWriteHandler(horizontalCellStyleStrategy).sheet(fileName).doWrite(data);
        } catch (Exception e) {
            logger.error("导出失败！", e);
            e.printStackTrace();
        }
    }

    /**
     * 读取excel
     *
     * @param inputStream 输入流
     * @param iCallback   回调方法
     */
    public static void read(InputStream inputStream, ICallback iCallback) {
        ExcelListener excelListenerV2 = new ExcelListener();
        excelListenerV2.setiCallback(iCallback);
        EasyExcel.read(inputStream, iCallback.getTClass(), excelListenerV2).sheet().doRead();
    }


    /**
     * 读取excel
     *
     * @param inputStream 输入流
     * @param iCallback   回调方法
     * @param batchCount  批量执行数据数量
     */
    public static void read(InputStream inputStream, ICallback iCallback, Integer batchCount) {
        ExcelListener excelListenerV2 = new ExcelListener();
        excelListenerV2.setiCallback(iCallback);
        excelListenerV2.setBatchCount(batchCount);
        EasyExcel.read(inputStream, iCallback.getTClass(), excelListenerV2).sheet().doRead();
    }

    /**
     * 读取excel
     *
     * @param inputStream 输入流
     * @param tClass      自定类型
     */
    public static <T> List<T> readAll(InputStream inputStream, Class<T> tClass) {
        return readAll(inputStream, tClass, 0);
    }


    /**
     * 读取excel
     *
     * @param inputStream 输入流
     * @param tClass      自定类型
     */
    public static <T> List<T> readAll(InputStream inputStream, Class<T> tClass, Integer sheetNo) {
        ExcelListener excelListener = new ExcelListener();
        EasyExcel.read(inputStream, tClass, excelListener).sheet(sheetNo).doRead();
        return excelListener.getDatas();
    }

}