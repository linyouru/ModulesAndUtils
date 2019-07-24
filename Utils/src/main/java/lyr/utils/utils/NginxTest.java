package lyr.utils.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nginx负载均衡测试类
 * @ClassName NginxTest
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/22 11:50
 * @Version 1.0
 **/
@RestController
public class NginxTest {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/getPort.do")
    public String getPort(){
        return "访问了端口"+port+"";
    }

}
