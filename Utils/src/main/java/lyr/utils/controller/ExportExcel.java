package lyr.utils.controller;

import lyr.utils.utils.EasyExcelUtils;
import lyr.utils.utils.ExcelReaderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/EasyExcelTest.do")
    public void EasyExcelTest(){
        //EasyExcelTestLittleDemo

        //导出
        String filepath = "C:\\Users\\asus\\Desktop\\EasyExcelTest.xlsx";
        File file = new File("C:\\Users\\asus\\Desktop\\EasyExcelTest.xlsx");
        List<List<String>> dataList = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        String sheetName = "testsheet1";
        list1.add("11111111111111111");
        list1.add("22222222222222222");
        list2.add("33333333333333333");
        list2.add("44444444444444444");
        dataList.add(list1);
        dataList.add(list2);
        EasyExcelUtils.writeExcelByString(filepath,sheetName,dataList);

        //读取
        Map<String, List<List<String>>> map =  EasyExcelUtils.readExcelByString(filepath);
        System.out.println(map);
    }

}
