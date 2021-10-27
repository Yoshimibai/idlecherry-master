package com.sydney.dao;

import com.sydney.entity.NoticeType;

public interface NoticeTypeMapper {
    int deleteByPrimaryKey(Integer noticetypeid);

    int insert(NoticeType record);

    int insertSelective(NoticeType record);

    NoticeType selectByPrimaryKey(Integer noticetypeid);

    int updateByPrimaryKeySelective(NoticeType record);

    int updateByPrimaryKey(NoticeType record);
}