package lyr.mybatisplus.service;

import lyr.mybatisplus.config.TargetDataSource;
import lyr.mybatisplus.entity.User;
import lyr.mybatisplus.dao.UserDao;
import lyr.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        List<User> all = userDao.getAll();
        for (User user : all) {
            System.out.println(user.toString());
        }

    }
}
