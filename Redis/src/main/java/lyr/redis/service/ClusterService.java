package lyr.redis.service;

import lyr.redis.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ClusterService
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/31 11:04
 * @Version 1.0
 **/
@Service
public class ClusterService {

    @Resource
    private RedisUtil redisUtil;

    public void setName (String name,String value){
        redisUtil.set(name,value);
    }

    public String getName(String name){

        return redisUtil.get(name)+"";
    }

    public void setNumbers(){

        for(int i=0;i<100;i++){
            redisUtil.set(i+"",i+"");
        }

        System.out.println("success");
    }


}
