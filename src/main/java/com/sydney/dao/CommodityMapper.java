package com.sydney.dao;

import com.sydney.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commid);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer commid);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    List<Commodity> selectByUserId(Integer userid);

    int putOnSaleById(Integer commID);

    int pullOffShelvesById(Integer commID);

    void register(Commodity commodity);

    List<Commodity> findLatestCommodity();

    List<Commodity> getAllCommodities();

    List<Commodity> getCommodityByCategory(Integer categoryID);

    List<Commodity> getCommodityBySearch(String searchC);

    Commodity getCommodityByName(String commName);

    int getUserIdByCommId(Integer commID);

}