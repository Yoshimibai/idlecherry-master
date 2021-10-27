package com.sydney.service;

import com.sydney.dao.UserDao;
import com.sydney.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signup(User user) {
        //1. email should be unique and search email whether is existed in database
        //User userDB = userDao.findByEmail(user.getEmail());
        //2. if user exists
        //if(!ObjectUtils.isEmpty(userDB)) throw new RuntimeException("already have this email account");
        //3. sign up an account
        userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User userDB = userDao.findByEmail(email);
        return userDB;
    }

    @Override
    public User getUserById(Integer userid) {
        User userDB = userDao.findById(userid);
        return userDB;
    }

    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public void updateImg(User user){
        userDao.updateImg(user);
    }

}
