package lyr.learn.io;

import java.io.*;

public class Byte2Char {

    public static void main(String[] args) throws IOException {
        Byte2Char byte2Char = new Byte2Char();
        byte2Char.byte2Char();
        byte2Char.char2Byte();
    }


    void byte2Char() throws IOException {
        File f =new File("./test.txt");
        // OutputStreamWriter 是字符流通向字节流的桥梁,创建了一个字符流通向字节流的对象
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
        outputStreamWriter.write("我是字符流转换成字节流输出的");
        outputStreamWriter.close();
    }

    void char2Byte() throws IOException{
        File f = new File("test.txt");

        InputStreamReader inr = new InputStreamReader(new FileInputStream(f), "UTF-8");

        char[] buf = new char[1024];
        int len = inr.read(buf);
        System.out.println(new String(buf,0,len));
        inr.close();
    }

}
