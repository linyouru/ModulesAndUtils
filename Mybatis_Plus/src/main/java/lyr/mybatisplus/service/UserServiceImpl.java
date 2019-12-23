package lyr.mybatisplus.service;

//import lyr.mybatisplus.config.TargetDataSource;
import lyr.mybatisplus.entity.User;
import lyr.mybatisplus.dao.UserDao;
import lyr.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
//        userDao.insert(user);
//        save(user);
//        ArrayList<User> userList = new ArrayList<>();
//        userList.add(user);
//        saveBatch(userList);


    }


}
