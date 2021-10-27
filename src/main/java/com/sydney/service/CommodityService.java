package com.sydney.service;


import com.sydney.entity.Commodity;

import java.util.List;

public interface CommodityService {

    Commodity queryCommodityByID(Integer id);

    List<Commodity> selectCommodityByUserId(Integer userId);

    void putOnSaleById(Integer id);

    void pullOffShelvesById(Integer id);

    void register(Commodity commodity);

    int updateByCommidSelective(Commodity commodity);

    List<Commodity> findLatestCommodity();

    List<Commodity> getAllCommodities();

    List<Commodity> getCommodityByCategory(Integer categoryID);

    List<Commodity> getCommodityBySearch(String searchC);

    Commodity getCommodityByName(String commName);

    int getUserIdByCommId(Integer commID);

}
