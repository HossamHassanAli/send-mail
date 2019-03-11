package com.z2data.sourcing.email.bean;

public class MailFeatures {
private String to;
private String cc;
private String subject;
private String mailContent;
private String attachFilepath;
private String from;
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getCc() {
	return cc;
}
public void setCc(String cc) {
	this.cc = cc;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMailContent() {
	return mailContent;
}
public void setMailContent(String mailContent) {
	this.mailContent = mailContent;
}
public MailFeatures(String to, String cc, String subject, String mailContent) {
	super();
	this.to = to;
	this.cc = cc;
	this.subject = subject;
	this.mailContent = mailContent;
}
public MailFeatures() {
    super();
}

public String getAttachFilepath() {
	return attachFilepath;
}
public void setAttachFilepath(String attachFilepath) {
	this.attachFilepath = attachFilepath;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}

}
