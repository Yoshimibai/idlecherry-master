package com.sydney.service;

import com.sydney.entity.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> selectByUserID(Integer userid);

    int insertSelective(Notice record);


}
