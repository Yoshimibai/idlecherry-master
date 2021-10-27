package com.sydney.service;

import com.sydney.dao.UserCollectionMapper;
import com.sydney.entity.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Override
    public List<UserCollection> selectCollectionByUserId(Integer userid) {
        return userCollectionMapper.selectByUserId(userid);
    }


    @Override
    public int insertSelective(UserCollection record) {
        return userCollectionMapper.insertSelective(record);
    }

    @Override
    public UserCollection selectByCommIDandUserID(Integer commid, Integer userid) {
        return userCollectionMapper.selectByCommIDandUserID(commid, userid);
    }

    @Override
    public int deleteByCommIDandUserID(Integer commid, Integer userid) {
        return userCollectionMapper.deleteByCommIDandUserID(commid,userid);
    }
}
