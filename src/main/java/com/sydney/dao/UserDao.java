package com.sydney.dao;

import com.sydney.entity.User;

public interface UserDao {

    //find user by email address
    User findByEmail(String email);

    //save user account information
    void save(User user);

    //get User by Id
    User findById(Integer userid);

    void update(User user);

    void updateImg(User user);
}
