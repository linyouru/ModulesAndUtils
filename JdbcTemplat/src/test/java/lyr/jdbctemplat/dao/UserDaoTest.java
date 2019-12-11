package lyr.jdbctemplat.dao;

import lyr.jdbctemplat.JdbcTemplatApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={JdbcTemplatApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// 指定启动类
@ContextConfiguration
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void demo1() {
        userDao.demo1();
    }

    @Test
    public void changeDataSource(){
        userDao.changeDataSource();
    }

    @Test
    public void dome2(){
        userDao.demo2();
    }

    @Test
    public void demo3(){
        userDao.demo3();
    }

}