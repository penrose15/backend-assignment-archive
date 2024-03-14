package aop.practice.service;

import aop.practice.domain.User;

public interface UserService {
    public void signup(User user);

    public User findUser(Long userId);

}
