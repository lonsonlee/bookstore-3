package com.risen.util;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	private static Properties prop=new Properties();
	private static Session session;
	
	
	public static void sendEmail(Properties mprop,String to) throws Exception{
		
		String host=mprop.getProperty("javaMail.host");
		String emailAccount=mprop.getProperty("javaMail.from");
		String password=mprop.getProperty("javaMail.password");
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	    prop.put("mail.smtp.host", host);
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.connectiontimeout", "20000");
        prop.setProperty("mail.smtp.timeout", "20000");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.quitwait", "false");
	    	
//		  prop.put("mail.smtp.user", "zhongrisen@gmail.com");
//        prop.put("mail.smtp.host", host);
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.starttls.enable","true");
//        prop.put("mail.smtp.debug", "true");
//        prop.put("mail.smtp.auth", "true");
       
		
//		prop.setProperty("mail.transport.protocol", "smtps");
//		prop.setProperty("mail.smtp.auth", "true");
//		prop.setProperty("mail.host", host);
//		prop.setProperty("mail.smtp.starttls.enable", "true");
//		prop.setProperty("mail.smtp.port", "587");
		
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.socketFactory.port", "465");
//		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.port", "465");
//		prop.setProperty("mail.smtp.starttls.enable", "true");

		
		/*
		 * 1.创建session
		 */
		session=Session.getInstance(prop,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(emailAccount, password);
			}
		});
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		/*
		 * 2.通过session得到Transport对象
		 */
		//Transport ts=session.getTransport();
		/*
		 * 3.通过邮箱账号和密码链接邮件服务器
		 */
		//ts.connect(host, emailAccount,password);
		/*
		 * 4.创建邮件对象
		 */
		Message message=getTextMail(session, mprop, to);
		/*
		 * 5.发送邮件
		 */
		//ts.sendMessage(message, message.getAllRecipients());
		//ts.close();
		Transport.send(message);
	}
	
	/**
	 * @param session
	 * @param mprop
	 * @return
	 *  生成邮件对象
	 * @throws Exception 
	 * @throws AddressException 
	 */
	public static Message getTextMail(Session session,Properties mprop,String to) throws AddressException, Exception{
		Message message=new MimeMessage(session);
		//指明发件人
		message.setFrom(new InternetAddress(mprop.getProperty("javaMail.from")));
		//指明收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//设置邮件的标题
		message.setSubject(mprop.getProperty("javaMail.subject"));
		//设置邮件内容
		message.setContent(mprop.getProperty("javaMail.content"),"text/html;charset=utf-8");
		
		return message;
	}
}
