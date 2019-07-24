package lyr.utils.controller;

import lyr.utils.utils.ExcelReaderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

/**
 * @ClassName test
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/24 14:51
 * @Version 1.0
 **/
@Controller
public class ExportExcel {

    @RequestMapping("/ImportData.do")
    public void ImportData(){

        File file = new File("C:\\Users\\LYR\\Desktop\\动环实时性能数据表.xlsx");
        List<List<Object>> lists = ExcelReaderUtil.readExcel(file, 0);
        System.out.println(123);

    }
}
