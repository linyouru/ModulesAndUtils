package lyr.utils.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 下载服务器上文件demo
 * @ClassName downloadFile
 * @Description TODO
 * @Author LYR
 * @Date 2020/3/10 10:55
 * @Version 1.0
 **/
@RestController
public class DownloadFile {

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //获取资源所在路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"template";
            path = path.substring(1,path.length());
            inputStream = new FileInputStream(path+File.separator+"test.txt");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            String fileName = URLEncoder.encode("自定义中文名.txt", "utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        response.setContentType("application/vnd.ms-easyExcel");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } finally {
            if(outputStream!=null){
                outputStream.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }

    }


}
