package lyr.utils.utils;


import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取excel
 */
public class ExcelReaderUtil {
    static Logger log=LoggerFactory.getLogger(ExcelReaderUtil.class);

    /**
     * 读Excel 根据sheet下标读取
     * @param file
     * @param sheetIndex sheet下标 从0开始
     * @return
     */
    public static List<List<Object>> readExcel(File file,int sheetIndex)  {
        FileInputStream in=null;
        try {
            in=new FileInputStream(file);
            Workbook workbook = getWorkbook(in, file);
            Sheet sheetAt = workbook.getSheetAt(sheetIndex);
            return read(sheetAt);
        }catch (Exception e){
            log.error("",e);
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("",e);
                }
            }
        }
        return null;
    }

    /**
     * 读excel 根据sheet名称读取
     * @param file
     * @param sheetName sheet名称
     * @return
     */
    public static List<List<Object>> readExcel(File file,String sheetName)  {
        FileInputStream in=null;
        try {
            in=new FileInputStream(file);
            Workbook workbook = getWorkbook(in, file);
            Sheet sheetAt = workbook.getSheet(sheetName);
            return read(sheetAt);
        }catch (IOException e){
            log.error("",e);
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("",e);
                }
            }
        }
        return null;
    }

    private static List<List<Object>> read(Sheet sheet){
        List<List<Object>> rs=new ArrayList<>();
        sheet.forEach(row -> {
            List<Object> rsRow=new ArrayList<>();
            row.forEach(cell -> {
                Object value = getValue(cell);
                rsRow.add(value);
            });
            rs.add(rsRow);
        });
        return rs;
    }

    /**
     *
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    private static Workbook getWorkbook(FileInputStream in, File file) throws IOException {
        Workbook workbook=null;
        if (file.getName().toLowerCase().endsWith("xls")){
            workbook=new HSSFWorkbook(in);
        }else if (file.getName().toLowerCase().endsWith("xlsx")){
//            workbook=new XSSFWorkbook(in);
            workbook=StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(in);
        }
        return workbook;
    }

    private static Object getValue(Cell cell) {
        Object obj = null;
        if (cell!=null){
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    obj = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    obj = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                        obj = cell.getDateCellValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = formatter.format(obj);
                        obj = time;
                    }else {
                        obj = cell.getNumericCellValue();
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    obj = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
        }
        return obj;
    }

}
