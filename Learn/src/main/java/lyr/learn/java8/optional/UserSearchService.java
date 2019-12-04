package lyr.learn.java8.optional;

import java.util.Optional;

/**
 * @ClassName UserSearchService
 * @Description TODO
 * @Author LYR
 * @Date 2019/11/21 15:31
 * @Version 1.0
 **/
public interface UserSearchService {

    Optional<User> getUser(int id);

}
