package com.sydney.service;

import com.sydney.dao.CommodityCommentMapper;
import com.sydney.entity.CommodityComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityCommentServiceImpl implements CommodityCommentService {
   @Autowired
    CommodityCommentMapper commodityCommentMapper;

   @Override
    public List<CommodityComment> selectCommentsByCommID(Integer commid) {
        return commodityCommentMapper.selectCommentsByCommID(commid);
    }

    @Override
    public int insertSelective(CommodityComment record) {
        return commodityCommentMapper.insertSelective(record);
    }
}
