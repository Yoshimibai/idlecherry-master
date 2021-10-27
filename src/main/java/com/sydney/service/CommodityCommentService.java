package com.sydney.service;

import com.sydney.entity.CommodityComment;

import java.util.List;

public interface CommodityCommentService {

    List<CommodityComment> selectCommentsByCommID(Integer commid);

    int insertSelective(CommodityComment record);
}
