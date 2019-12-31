package lyr.redis.service;

import lyr.redis.entity.Tuser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 注解使用缓存测试
 * @ClassName UserService
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/25 17:51
 * @Version 1.0
 **/
@Service
public class UserService {

    /**
     * 这里在使用缓存的时候会报一个强转错误，详细原因在笔记上
     * @Date 15:41 2019/12/26
     **/
    @Cacheable(value = "user")
    public Tuser getUser(){
        Tuser user = new Tuser();
        user.setScore(111);
        user.setAge(100);
        user.setName("测试");
        System.out.println("从service返回user");
        return user;
    }

    /**
     * 将参数与返回值以key-value的形式进行缓存，相同的参数不再执行方法直接从缓存中返回值
     * @Author LinYouRu
     **/
    @Cacheable(value = "msg")
    public String getMsg(){
        System.out.println("从service返回Msg");
        return "Test Msg";

    }

    /**
     * 清除缓存
     * @Author LinYouRu
     **/
    @CacheEvict(value = "msg")
    public void clearMsgCache(){
        System.out.println("清除缓存的信息");
    }


}
