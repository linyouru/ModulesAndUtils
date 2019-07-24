package lyr.utils.controller;

import lyr.utils.utils.ExportWriterUtil;
import lyr.utils.utils.UuidUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ImportExcel
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/24 14:54
 * @Version 1.0
 **/
public class ImportExcel {

    public void ExportData(HttpServletResponse response) throws IOException {

        String path = "temp"+File.separator; //excel临时文件路径
        File excelFile = new File(path + UuidUtil.randomUUID()+".xlsx");	//创建excel文件
        excelFile.createNewFile();  //创建excel文件
        List<Map<String, Object>> head = new ArrayList<>(); //表头
        List<Map<String, Object>> data = new ArrayList<>();	//数据
        OutputStream os = new FileOutputStream(excelFile);
        ExportWriterUtil.exportExcel(head, data, os); //将数据写入excel文件

        response.addHeader("Content-Length", "" + excelFile.length());
        response.setContentType("application/octet-stream");
        String exprotName = URLEncoder.encode("导出文件名", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + exprotName + ".xlsx");
        OutputStream out = response.getOutputStream();
        InputStream inputStream=new FileInputStream(excelFile);
        byte[] bytes = new byte[1024*4];
        int len;
        while ((len = inputStream.read(bytes)) > 0) {
            out.write(bytes, 0, len);
            out.flush();
        }
        out.close();
        inputStream.close();
        excelFile.delete(); //删除临时文件

    }
}
