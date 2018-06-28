package com.njfsyk.mail.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

 

import com.njfsyk.mail.model.system.SysEmail;
import com.njfsyk.mail.model.system.SysEmailHistory;
import com.njfsyk.mail.service.system.SysEmailService;

 
public class SpringQtz {

	protected void execute()
	{
		long ms = System.currentTimeMillis();
		//System.out.println("\t\t" + new Date(ms));

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		SysEmailService sysEmailService = (SysEmailService) wac.getBean("sysEmailService");
		List<SysEmail> needToSendEmailList = sysEmailService.queryAllSysEmail(); 
		if (!needToSendEmailList.isEmpty())
		{
			for (SysEmail email : needToSendEmailList)
			{
				Short sendTimes = email.getIsSend();
				if (sendTimes < 3)
				{
					String emailArray[] = email.getReceiveEmail().split(",");
					
					
					SendEmailToSomeUtil util = new SendEmailToSomeUtil();

					try
					{
						String[] filePath = {email.getFileUrl1(),email.getFileUrl2(),email.getFileUrl3()};
						
						util.SendEmail(emailArray, email.getReceiveName(), email.getApplyReason(),
								email.getSubject(),filePath);
					} catch (Exception e)
					{
						try
						{
							email.setIsSend((short) (sendTimes + (short) 1));
							sysEmailService.updateSysEmailByPrimaryKeySelective(email);
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
						e.printStackTrace();
						continue;
					}
					
					try
					{
						sysEmailService.deleteSysEmail(email.getId());
						SysEmailHistory emailHistory = new SysEmailHistory();
						emailHistory.setId(email.getId());
						emailHistory.setReceiveId(email.getReceiveId());
						emailHistory.setReceiveEmail(email.getReceiveEmail());
						emailHistory.setApplyId(email.getApplyId());
						emailHistory.setApplyReason(email.getApplyReason());
						emailHistory.setCreateTime(email.getCreateTime());
						emailHistory.setSendTime(new Date());
						emailHistory.setSubject(email.getSubject());
						emailHistory.setReceiveName(email.getReceiveName());
						emailHistory.setFileUrl1(email.getFileUrl1());
						emailHistory.setFileUrl2(email.getFileUrl2());
						emailHistory.setFileUrl3(email.getFileUrl3());
						sysEmailService.insertSysEmailHistory(emailHistory);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				} else
				{
//					try
//					{
//						sysEmailService.deleteSysEmail(email.getId());
//						SysEmailHistory emailHistory = new SysEmailHistory();
//						emailHistory.setId(email.getId());
//						emailHistory.setReceiveId(email.getReceiveId());
//						emailHistory.setReceiveEmail(email.getReceiveEmail());
//						emailHistory.setApplyId(email.getApplyId());
//						emailHistory.setApplyReason(email.getApplyReason());
//						emailHistory.setCreateTime(email.getCreateTime());
//						emailHistory.setSendTime(new Date());
//						emailHistory.setSubject(email.getSubject());
//						emailHistory.setReceiveName(email.getReceiveName());
//						sysEmailService.insertSysEmailHistory(emailHistory);
//					} catch (Exception e)
//					{
//						e.printStackTrace();
//					}
				}
			}
		}
	}
}
