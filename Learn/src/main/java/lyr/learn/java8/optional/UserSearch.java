package lyr.learn.java8.optional;

import java.util.Optional;

/**
 * @ClassName UserSearch
 * @Description TODO
 * @Author LYR
 * @Date 2019/11/21 17:00
 * @Version 1.0
 **/
public class UserSearch implements UserSearchService{

    User user1 = new User(1,"lin");
    User user2 = null;



    @Override
    public Optional<User> getUser(int id) {
        return Optional.ofNullable(user1);
    }

    public static void main(String[] args) {
        UserSearch userSearch = new UserSearch();
//        Optional<User> user = userSearch.getUser(1);
        userSearch.getUser(1).ifPresent(u -> System.out.println(u.getName()));
        /*if(user.isPresent()){
            User user3 = user.get();
            System.out.println(user3.toString());
        }*/


    }


}
