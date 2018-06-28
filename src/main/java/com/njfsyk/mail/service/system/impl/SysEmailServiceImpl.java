package com.njfsyk.mail.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njfsyk.mail.dao.system.SysEmailHistoryMapper;
import com.njfsyk.mail.dao.system.SysEmailMapper;
import com.njfsyk.mail.model.system.SysEmail;
import com.njfsyk.mail.model.system.SysEmailHistory;
import com.njfsyk.mail.service.system.SysEmailService;

@Service("sysEmailService")
public class SysEmailServiceImpl implements SysEmailService
{
	@Autowired
	private SysEmailMapper sysEmailMapper;
	@Autowired
	private SysEmailHistoryMapper sysEmailHistoryMapper;

	@Override
	public int deleteSysEmail(Integer id)
	{
		return sysEmailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSysEmail(SysEmail record)
	{
		return sysEmailMapper.insert(record);
	}

	@Override
	public SysEmail selectSysEmailByPrimaryKey(Integer id)
	{
		return sysEmailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSysEmailByPrimaryKeySelective(SysEmail record)
	{
		return sysEmailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysEmail> queryAllSysEmail()
	{
		return sysEmailMapper.queryAllSysEmail();
	}

	@Override
	public int insertSysEmailHistory(SysEmailHistory record)
	{
		return sysEmailHistoryMapper.insertSelective(record);
	}

}
