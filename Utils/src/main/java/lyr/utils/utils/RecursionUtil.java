package lyr.utils.utils;

import java.io.File;
import java.util.List;

/**
 * 递归工具类
 * @ClassName RecursionUtil
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/12 15:57
 * @Version 1.0
 **/
public class RecursionUtil {


    /**
     * 递归遍历文件夹，获取全部文件路径
     * @param folderPath 文件夹路径
     * @param pahtList 文件路径集合
     * @param nameList 文件名集合
     * @Author LinYouRu
     * @Date 16:42 2019/7/12
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static void getFilePath(String folderPath,List<String> pahtList,List<String> nameList) throws Exception {

        File file = new File(folderPath);
        if (file.exists()) {
            File[] files = file.listFiles();	//获取文件列表
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {	//遍历文件列表
                    if(file2.isDirectory()) {
                        getFilePath(file2.getAbsolutePath(),pahtList,nameList);
                    }
                    pahtList.add(file2.getAbsolutePath());
                    nameList.add(file2.getName());
                }
            }
        } else {
            throw new Exception("文件不存在!");
        }
    }

    public static void getFilePath(String folderPath,List<String[]> data) throws Exception {

        File file = new File(folderPath);
        if (file.exists()) {
            File[] files = file.listFiles();	//获取文件列表
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {	//遍历文件列表
                    if(file2.isDirectory()) {
                        getFilePath(file2.getAbsolutePath(),data);
                    }else {
                        String[] str = new String[3];
                        str[0] =  file2.getName().substring(0,file2.getName().lastIndexOf("-"));
                        str[1] = file2.getName();
                        str[2] = file2.getAbsolutePath();
                        data.add(str);
                    }
                }
            }
        } else {
            throw new Exception("文件不存在!");
        }

    }
}
