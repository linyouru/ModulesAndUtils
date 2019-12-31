package lyr.redis.contrller;

import lyr.redis.service.ClusterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * redis集群测试类
 * @ClassName ClusterController
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/31 11:03
 * @Version 1.0
 **/
@RestController
public class ClusterController {

    @Resource
    private ClusterService clusterService;

    @RequestMapping("/setName")
    public void setName(){
        clusterService.setName("lyr","linyouru");
    }

    @RequestMapping("/getName")
    public String getName(){
        return clusterService.getName("lyr");
    }

    @RequestMapping("/setNumbers")
    public void setNumbers(){
        clusterService.setNumbers();
    }

}
