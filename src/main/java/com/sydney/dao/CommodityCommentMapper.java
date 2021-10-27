package com.sydney.dao;

import com.sydney.entity.CommodityComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityCommentMapper {
    int deleteByPrimaryKey(Integer commentid);

    int insert(CommodityComment record);

    int insertSelective(CommodityComment record);

    CommodityComment selectByPrimaryKey(Integer commentid);

    int updateByPrimaryKeySelective(CommodityComment record);

    int updateByPrimaryKey(CommodityComment record);

    List<CommodityComment> selectCommentsByCommID(Integer commid);


}