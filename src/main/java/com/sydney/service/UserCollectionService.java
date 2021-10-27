package com.sydney.service;

import com.sydney.entity.UserCollection;

import java.util.List;

public interface UserCollectionService {

    List<UserCollection> selectCollectionByUserId(Integer Userid);

    int insertSelective(UserCollection record);

    UserCollection selectByCommIDandUserID(Integer commid, Integer userid);

    int deleteByCommIDandUserID(Integer commid, Integer userid);
}
