package com.njfsyk.mail.model.system;

import java.util.Date;

public class SysEmail
{
	private Integer id;

	private Integer receiveId;

	private String receiveEmail;

	private Integer applyId;

	private String applyReason;

	private Date createTime;

	private Short isSend;

	private String subject;

	private String receiveName;
	
	private String key;
	
	private String fileUrl1;
	private String fileUrl2;
	private String fileUrl3;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getReceiveId()
	{
		return receiveId;
	}

	public void setReceiveId(Integer receiveId)
	{
		this.receiveId = receiveId;
	}

	public String getReceiveEmail()
	{
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail)
	{
		this.receiveEmail = receiveEmail == null ? null : receiveEmail.trim();
	}

	public Integer getApplyId()
	{
		return applyId;
	}

	public void setApplyId(Integer applyId)
	{
		this.applyId = applyId;
	}

	public String getApplyReason()
	{
		return applyReason;
	}

	public void setApplyReason(String applyReason)
	{
		this.applyReason = applyReason == null ? null : applyReason.trim();
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Short getIsSend()
	{
		return isSend;
	}

	public void setIsSend(Short isSend)
	{
		this.isSend = isSend;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getReceiveName()
	{
		return receiveName;
	}

	public void setReceiveName(String receiveName)
	{
		this.receiveName = receiveName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFileUrl1() {
		return fileUrl1;
	}

	public void setFileUrl1(String fileUrl1) {
		this.fileUrl1 = fileUrl1;
	}

	public String getFileUrl2() {
		return fileUrl2;
	}

	public void setFileUrl2(String fileUrl2) {
		this.fileUrl2 = fileUrl2;
	}

	public String getFileUrl3() {
		return fileUrl3;
	}

	public void setFileUrl3(String fileUrl3) {
		this.fileUrl3 = fileUrl3;
	}

}