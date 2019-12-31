package lyr.redis.contrller;

import lyr.redis.entity.Tuser;
import lyr.redis.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UsetContrller
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/25 17:44
 * @Version 1.0
 **/
@RestController
public class UsetContrller {

    @Resource
    private UserService service;


    @RequestMapping("/getUser")
    public void getUser(){

        Tuser user = service.getUser();
        System.out.println(user.toString());
    }

    @RequestMapping("/getMsg")
    public void getMsg(){
        System.out.println(service.getMsg());
    }

    @RequestMapping("/clearMsgCache")
    public void clearMsgCache(){
        service.clearMsgCache();
    }

}
