package lyr.mybatisplus.service;

//import lyr.mybatisplus.config.TargetDataSource;
import lyr.mybatisplus.entity.User;
import lyr.mybatisplus.dao.UserDao;
import lyr.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyr
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 动态切换数据源
     * @Date 10:41 2019/12/24
     **/
    @Override
    public void getAll() {
        List<User> all = userDao.getAllToPgsql();
        System.out.println("Pgsql");
        for (User user : all) {
            System.out.println(user.toString());
        }
        List<User> allToMysql = userDao.getAllToMysql();
        System.out.println("Mysql");
        for (User user : allToMysql) {
            System.out.println(user.toString());
        }
    }


    /**
     * 使用dao层默认CRUD
     * 使用AOP多数据源切换后无法使用默认CRUD操作，原因:sqlSessionFactory替换成mybatis-plusd的MybatisSqlSessionFactoryBean
     * @Date 10:17 2019/12/23
     **/
    @Override
    public void getuser(){
        List<User> users = userDao.selectList(null);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    /**
     * 使用service层默认CRUD
     * @Date 10:42 2019/12/24
     **/
    @Override
    public void crudDemo(){
        User user = new User();
        user.setName("回事");
        user.setAge(532);
        user.setEmail("123@ww.com");

        List<User> list = list();
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    /**
     * 传参测试
     * @Date 10:24 2019/12/24
     **/
    @Override
    public void getUser() throws JSONException {

        //1、多个参数查询_匿名顺序传参
        List<User> user = userDao.findByParams(1, "林佑儒");

        //2、使用@Param注解
//        List<User> user = userDao.findByParams2(1, "林佑儒");

        //3、使用Map传递参数
//        HashMap<String,Object> params = new HashMap(2);
//        params.put("id",1);
//        params.put("name","林佑儒");
//        List<User> user = userDao.findByMapParams(params);

        //4、使用java bean传递多个参数
//        User params = new User(3,"个人",654,"dfe");
//        List<User> user = userDao.findByBeans(params);

        //5、使用JSON传递参数(使用同java bean，但这里Json对象有点问题)
//        JSONObject json = new JSONObject();
//        json.put("id", 1);
//        json.put("name","林佑儒");
//        List<User> user = userDao.findByJSONObject(json);

        //6、传递集合类型参数List、Set、Array
//        List params = new ArrayList();
//        params.add(1);
//        params.add(2);
//        List<User> user = userDao.findByList(params);

        for (User u : user) {
            System.out.println("查询用户————————————————————》"+u.toString());
        }

    }

}
