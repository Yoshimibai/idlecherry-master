package com.sydney.service;

import com.sydney.dao.CommodityMapper;
import com.sydney.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommodityServiceImpl implements CommodityService {


    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Commodity queryCommodityByID(Integer id) {
        return commodityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Commodity> selectCommodityByUserId(Integer userId) {
        return commodityMapper.selectByUserId(userId);
    }

    @Override
    public void putOnSaleById(Integer id) {
        commodityMapper.putOnSaleById(id);
    }

    @Override
    public void pullOffShelvesById(Integer id) {
        commodityMapper.pullOffShelvesById(id);
    }

    @Override
    public int updateByCommidSelective(Commodity commodity) {
        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }

    @Override
    public void register(Commodity commodity) {
        commodityMapper.register(commodity);
    }

    @Override
    public List<Commodity> findLatestCommodity() {
        List<Commodity> commodities = commodityMapper.findLatestCommodity();
        List<Commodity> six = new ArrayList<Commodity>();
        for (int i = 0; i <6; i++){
            six.add(commodities.get(i));
        }
        return six;
    }

    @Override
    public List<Commodity> getAllCommodities() {
        List<Commodity> commodities = commodityMapper.getAllCommodities();
        return commodities;
    }

    @Override
    public List<Commodity> getCommodityByCategory(Integer categoryID) {
        List<Commodity> commodities = commodityMapper.getCommodityByCategory(categoryID);
        return commodities;
    }

    @Override
    public List<Commodity> getCommodityBySearch(String searchC) {
        List<Commodity> commodities = commodityMapper.getCommodityBySearch(searchC);
        return commodities;
    }

    @Override
    public Commodity getCommodityByName(String commName) {
        Commodity commodity = commodityMapper.getCommodityByName(commName);
        return commodity;
    }

    @Override
    public int getUserIdByCommId(Integer commID) {
        int id = commodityMapper.getUserIdByCommId(commID);
        return id;
    }

}
