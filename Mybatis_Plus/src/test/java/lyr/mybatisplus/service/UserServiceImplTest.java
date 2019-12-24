package lyr.mybatisplus.service;

import lyr.mybatisplus.MybatisPlusApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest(classes = MybatisPlusApplication.class)
@RunWith(SpringRunner.class)
@ContextConfiguration
public class UserServiceImplTest {

    @Resource
    private UserService service;

    @Test
    public void getAll() {
        service.getAll();
    }

    @Test
    public void getuser() {

        service.getuser();
    }

    @Test
    public void crudDemo(){
        service.crudDemo();
    }

    @Test
    public void test1() throws JSONException {

        service.getUser();

    }
}