package lyr.mybatisplus.dao;

import lyr.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyr
 * @since 2019-07-29
 */
public interface UserDao extends BaseMapper<User> {

    List<User> getAll();

}
