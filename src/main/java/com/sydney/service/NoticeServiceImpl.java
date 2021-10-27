package com.sydney.service;

import com.sydney.dao.NoticeMapper;
import com.sydney.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> selectByUserID(Integer userid) {
        return noticeMapper.selectByUserID(userid);
    }

    @Override
    public int insertSelective(Notice record) {
        return noticeMapper.insertSelective(record);
    }
}
