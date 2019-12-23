package lyr.jdbctemplat.dao;

import lyr.jdbctemplat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 使用Jdbctemplate获取数据
 * @ClassName UserDao
 * @Description TODO
 * @Author LYR
 * @Date 2019/12/11 9:29
 * @Version 1.0
 **/
@Repository
public class UserDao {

//    @Resource
//    private JdbcTemplate jdbcTemplate;

    /**最简应用*/
    public void demo1(){

//        String sql = "select * from user";
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    }

    /**手动改变数据源*/
    public void changeDataSource(){
        //设置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rjtx_dw?serverTimezone=GMT%2B8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //构建指定数据源的JdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql ="select * from s_user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

    }

    /**注入使用不同数据源的JdbcTemplate*/

    @Autowired
    @Qualifier("jdbcTemplateOne")
    private JdbcTemplate jdbcTemplateOne;

    @Resource(name = "jdbcTemplatetwo")
    private JdbcTemplate jdbcTemplateTwo;

    public void demo2(){

        String sql1 = "select * from user";
        String sql2 = "select * from s_user";

        List<Map<String, Object>> list1 = jdbcTemplateOne.queryForList(sql1);
        List<Map<String, Object>> list2 = jdbcTemplateTwo.queryForList(sql2);
        System.out.println(111);

    }

    /**
     * 将查询结果映射到实体类中
     **/
    public void demo3(){
        String sql1 = "select * from user";
        List<User> users = jdbcTemplateOne.query(sql1, BeanPropertyRowMapper.newInstance(User.class));
    }

    /**
     * 操行分
     **/
    void demo4(){
        String sql = "select name,item from t2";
        List<Map<String, Object>> list = jdbcTemplateOne.queryForList(sql);
        for (Map<String, Object> map : list) {
            String name = map.get("name") + "";
            String item = map.get("item") + "";
            if (!"null".equals(item)) {
                String[] fenshu = item.split("\n");
                int sum = 80;
                for (String s : fenshu) {
                    int i = Integer.valueOf(s);
                    sum +=i;
                }
                System.out.println(name+"："+sum);
            }else {
                System.out.println(name+"：没有值可以计算");
            }

        }

    }

}
