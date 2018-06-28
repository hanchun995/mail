package com.njfsyk.mail.quartz;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
 





import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.njfsyk.mail.model.system.SysEmail;
import com.njfsyk.mail.model.system.SysUser;
import com.njfsyk.mail.model.system.Consts.*;
 

public class SendEmailUtil {

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    public static String myEmailAccount = SendEmail.FSYKEMAIL;
    public static String myEmailPassword = SendEmail.FSYKPWD;
 

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    //邮箱的 SMTP 服务器地址为
    public static String myEmailSMTPHost = SendEmail.FSYKSMTP;

    // 收件人邮箱（替换为自己知道的有效邮箱）
    //public static String receiveMailAccount = "xx@xx.com";

	public void SendEmail(String receiveMailAccount,String receiveName,String MessageBody,String subject,String[] filePath) {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关
        
        props.setProperty("mail.smtp.ssl.enable", "true"); 
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,receiveName,MessageBody,subject,filePath);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = null;
		try {
			transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        try {
			transport.connect(myEmailAccount, myEmailPassword);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 7. 关闭连接
			try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	
	 /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String receiveName,String MessageBody,String subject,String[] filePath) {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        MimeMessageHelper messageHelper = null;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		} catch (MessagingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 

        // 2. From: 发件人
        try {
			message.setFrom(new InternetAddress(sendMail, "南京丰生永康", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        try {
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        // 4. Subject: 邮件主题
        try {
			message.setSubject(subject, "UTF-8");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        // 5. Content: 邮件正文（可以使用html标签）
        //message.setContent("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8");

        //发送邮件
                       /*  StringBuilder sb = new StringBuilder();
                         sb.append("亲爱的" + receiveName + "您好：<br/><br/>");
                         sb.append("点击以下链接设置新密码。<br/><br/>");
                         sb.append("<a href =\" http://127.0.0.1:8080/DMS/user/findPwdByEmailPage?loginName=" + receiveName +"\">http://127.0.0.1:8080/DMS/user/findPwdByEmailPage?loginName=zhuke" +"</a><br/><br/>");
                         sb.append("我们将一如既往、热忱的为您服务！<br/><br/>");
                         String MessageBody = sb.toString();*/
        
                       /*  try {
							message.setContent(MessageBody,"text/html;charset=UTF-8");
						} catch (MessagingException e1) {
							e1.printStackTrace();
						}*/
        //添加附件
		if (filePath != null) {
			BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
			try {
				mdp.setContent(MessageBody, "text/html;charset=UTF-8");
				// 给BodyPart对象设置内容和格式/编码方式
				Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对象
				mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
				// 把mm作为消息对象的内容
				MimeBodyPart filePart;
				FileDataSource filedatasource;
				// 逐个加入附件
				for (int j = 0; j < filePath.length; j++) {
					if(filePath[j]!=null){
						File file = new File(filePath[j]);
						if(!file.exists()){
							continue;
						}
						filePart = new MimeBodyPart();
						filedatasource = new FileDataSource(filePath[j]);
						filePart.setDataHandler(new DataHandler(filedatasource));
						try {
							filePart.setFileName(MimeUtility.encodeText(filedatasource
									.getName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						mm.addBodyPart(filePart);
					}
				}
				message.setContent(mm);
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				messageHelper.setText(MessageBody, true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
                         
        // 6. 设置发件时间
        try {
			message.setSentDate(new Date());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        // 7. 保存设置
        try {
			message.saveChanges();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        return message;
    }
    
    public SysEmail createEmailHistoryInstance(SysUser user,Integer applyId,String ApplyReason){
    	 Date now = new Date();
    	SysEmail emailHistory = new SysEmail();
		emailHistory.setReceiveId(user.getId());
		emailHistory.setReceiveEmail(user.getEmail());
		emailHistory.setApplyId(applyId);
		emailHistory.setApplyReason(ApplyReason);
		emailHistory.setCreateTime(now);
//		emailHistory.setSendTime(now);
		return emailHistory;
    }
    
    public SysEmail createEmailInstance(SysUser user,Integer id,Integer applyId,String ApplyReason){
   	    Date now = new Date();
	   	SysEmail email = new SysEmail();
		email.setId(id);
		email.setReceiveId(user.getId());
		email.setReceiveEmail(user.getEmail());
		email.setApplyId(applyId);
		email.setApplyReason(ApplyReason);
		email.setCreateTime(now);
		email.setIsSend(IsSend.SENDED_ERROR);
		return email;
   }
}
