package lyr.utils.utils;

import java.io.*;
import java.time.LocalDateTime;

/**
 * txt文件工具类
 * @ClassName TxtUtil
 * @Description TODO
 * @Author LYR
 * @Date 2020/2/5 9:28
 * @Version 1.0
 **/
public class TxtUtil {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\LYR\\Desktop\\new 2.txt");
        readTxt(file);
    }

    public static void readTxt(File file)  {
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] split = s.split("\t");
                for (String s1 : split) {
                    System.out.println(s1);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readTxt1(File file)  {
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                s = s.replace("\t",";//");
                System.out.println("private String "+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
