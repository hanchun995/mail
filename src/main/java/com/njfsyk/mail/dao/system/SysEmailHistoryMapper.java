package com.njfsyk.mail.dao.system;

import com.njfsyk.mail.model.system.SysEmailHistory;

public interface SysEmailHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmailHistory record);

    int insertSelective(SysEmailHistory record);

    SysEmailHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmailHistory record);

    int updateByPrimaryKey(SysEmailHistory record);
}