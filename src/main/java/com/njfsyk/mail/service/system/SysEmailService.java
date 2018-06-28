package com.njfsyk.mail.service.system;

import java.util.List;

import com.njfsyk.mail.model.system.SysEmail;
import com.njfsyk.mail.model.system.SysEmailHistory;

public interface SysEmailService
{
	int deleteSysEmail(Integer id);

	int insertSysEmail(SysEmail record);

	SysEmail selectSysEmailByPrimaryKey(Integer id);

	int updateSysEmailByPrimaryKeySelective(SysEmail record);

	List<SysEmail> queryAllSysEmail();

	int insertSysEmailHistory(SysEmailHistory record);

}
