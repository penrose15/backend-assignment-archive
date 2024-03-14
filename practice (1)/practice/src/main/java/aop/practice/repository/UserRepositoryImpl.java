package aop.practice.repository;

import aop.practice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    Map<Long, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public User findById(Long id) {

        return userMap.get(id);
    }

}
