package com.sydney.service;

import com.sydney.entity.User;

public interface UserService {

    //sign up a user account
    void signup(User user);

    User getUserByEmail(String email);

    User getUserById(Integer userid);

    void update(User user);

    void updateImg(User user);

}
