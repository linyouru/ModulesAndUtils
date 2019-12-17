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

    @Override
    public void getuser(){
        List<User> users = userDao.selectList(null);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Override
    public void CrudDemo(){
        User user = new User();
//        user.setId(5);
        user.setName("批量插入");
        user.setAge(55);
        user.setEmail("qwe@ww.com");

//        save(user);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        saveBatch(userList);


    }


}
