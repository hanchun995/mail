package com.njfsyk.mail.dao.system;

import java.util.List;

import com.njfsyk.mail.model.system.SysEmail;

public interface SysEmailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmail record);

    SysEmail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmail record);
    
    List<SysEmail> queryAllSysEmail();

}