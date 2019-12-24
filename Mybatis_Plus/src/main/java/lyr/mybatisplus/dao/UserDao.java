package lyr.mybatisplus.dao;

import lyr.mybatisplus.config.TargetDataSource;
import lyr.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.HashMap;
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

    @TargetDataSource(dataSource = TargetDataSource.POSTGRESQL)
    List<User> getAllToPgsql();

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> getAllToMysql();

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByParams(int id,String name);

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByParams2(@Param("id") int id, @Param("name") String name);

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByMapParams(HashMap<String,Object> params);

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByBeans(User params);

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByJSONObject(JSONObject json);

    @TargetDataSource(dataSource = TargetDataSource.MYSQL)
    List<User> findByList(List params);
}
