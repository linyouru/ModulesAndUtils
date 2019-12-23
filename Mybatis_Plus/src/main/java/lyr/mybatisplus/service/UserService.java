package lyr.mybatisplus.service;

import lyr.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyr
 * @since 2019-07-29
 */
public interface UserService extends IService<User> {

    void getAll();

    void getuser();

    /**
     * service层CRUDdemo
     * @Author LinYouRu
     * @Date 10:02 2019/12/16
     * @return void
     **/
    void crudDemo();
}
