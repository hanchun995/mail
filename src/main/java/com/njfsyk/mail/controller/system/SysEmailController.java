package com.njfsyk.mail.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njfsyk.mail.common.util.KeyUtils;
import com.njfsyk.mail.model.system.SysEmail;
import com.njfsyk.mail.service.system.SysEmailService;

@Controller
@RequestMapping("/mail")
public class SysEmailController {
 
	@Autowired
	private SysEmailService sysEmailService;
	
  @ResponseBody
  @RequestMapping(value = "/sendEmail")
  public String sendEmail(@RequestParam Integer receiveId,@RequestParam String receiveEmail,
		  @RequestParam Integer applyId,@RequestParam String applyReason,@RequestParam Short isSend,
		  @RequestParam String subject,@RequestParam String receiveName,@RequestParam String fileUrl1,
		  @RequestParam String fileUrl2,@RequestParam String fileUrl3,@RequestParam String key){
	  String status = "fail";
	  
	  SysEmail email = new SysEmail();
	  email.setReceiveId(receiveId);
	  email.setReceiveEmail(receiveEmail);
	  email.setApplyId(applyId);
	  email.setApplyReason(applyReason);
	  email.setIsSend(isSend);
	  email.setSubject(subject);
	  email.setReceiveName(receiveName);
	  email.setFileUrl1(fileUrl1 == ""?null:fileUrl1);
	  email.setFileUrl2(fileUrl2 == ""?null:fileUrl2);
	  email.setFileUrl3(fileUrl3 == ""?null:fileUrl3);
	  email.setKey(key);
	  email.setCreateTime(new Date());
	  
	  if(!key.equals(KeyUtils.getKey())){
		  return status;
	  }
	  
	  Integer result = sysEmailService.insertSysEmail(email);
	  if(result >0){
		  status = "ok";
	  }
	  
	 return status;
  }
}
