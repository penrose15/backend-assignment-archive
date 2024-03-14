package aop.practice.repository;

import aop.practice.domain.User;

public interface UserRepository {
    public void save(User user);

    public User findById(Long id);


}
