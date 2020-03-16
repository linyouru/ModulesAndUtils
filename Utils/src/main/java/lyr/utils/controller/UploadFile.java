package lyr.utils.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 上传文件至服务器demo
 * @ClassName UploadFile
 * @Description TODO
 * @Author LYR
 * @Date 2020/3/10 11:10
 * @Version 1.0
 **/
@RestController
public class UploadFile {

    public void upload(HttpServletRequest request, MultipartFile file) throws IOException {

        //保存文件路径
        String filepath = request.getSession().getServletContext().getRealPath("")+File.separator+file.getOriginalFilename();
        File dest = new File(filepath);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            //不存在则创建
            dest.getParentFile().mkdir();
        }
        file.transferTo(dest);
    }

}
