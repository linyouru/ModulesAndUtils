import lyr.mybatisplus.MybatisPlusApplication;
import lyr.mybatisplus.dao.UserDao;
import lyr.mybatisplus.entity.User;
import lyr.mybatisplus.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName test
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/29 10:12
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MybatisPlusApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// 指定启动类
@ContextConfiguration
public class test {

    @Resource
    private UserDao userDao;

    @Resource
    private UserService userSevice;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void test(){
        userSevice.getAll();
    }


}
