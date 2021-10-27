package com.sydney.dao;

import com.sydney.entity.CommodityCategory;

public interface CommodityCategoryMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(CommodityCategory record);

    int insertSelective(CommodityCategory record);

    CommodityCategory selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(CommodityCategory record);

    int updateByPrimaryKey(CommodityCategory record);
}