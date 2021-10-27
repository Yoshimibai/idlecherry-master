package com.sydney.dao;

import com.sydney.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserCollectionMapper {
    int deleteByPrimaryKey(Integer collectionid);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    UserCollection selectByPrimaryKey(Integer collectionid);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);

    List<UserCollection> selectByUserId(Integer userid);

    UserCollection selectByCommIDandUserID(Integer commid, Integer userid);

    int deleteByCommIDandUserID(Integer commid, Integer userid);
}