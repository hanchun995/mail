package com.njfsyk.mail.model.system;

public interface Consts
{
 

	public static interface SendEmail
	{
		String FSYKEMAIL = ;// 发�?邮件�?
		String FSYKPWD = ; // 密码
		String FSYKSMTP = ; // fsyk �?SMTP 服务器地�?
	}

	public static interface IsSend
	{
		Short NOT_SEND = 0; // 待发�?
		Short SENDED_ERROR = 1; // 发�?失败，需要重新发�?
	}

	 
}
