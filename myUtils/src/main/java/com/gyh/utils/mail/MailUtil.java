package com.gyh.utils.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.gyh.utils.PropertiesFileUtil;

public class MailUtil {

	public static void sendMail(String toEmail, String subject, String htmlContent) throws Exception {
		PropertiesFileUtil fileUtil=new PropertiesFileUtil();
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		
		//为验证码封装html
		htmlContent = getHtmlContent(htmlContent);

		// 发送邮箱的邮件服务器
		senderImpl.setHost(fileUtil.QueryValue("/configuration.properties","mail.host"));
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 为防止乱码，添加编码集设置
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
		messageHelper.setReplyTo(fileUtil.QueryValue("/configuration.properties","mail.from"));
		messageHelper.setSentDate(new Date());

		try {
			// 接收方邮箱
			messageHelper.setTo(toEmail);
		} catch (MessagingException e) {
			throw new Exception("收件人邮箱地址出错！");
		}
		try {
			// 发送方邮箱
			messageHelper.setFrom(fileUtil.QueryValue("/configuration.properties","mail.from"));
		} catch (MessagingException e) {
			throw new Exception("发件人邮箱地址出错！");
		}
		try {
			messageHelper.setSubject(subject);
		} catch (MessagingException e) {
			throw new Exception("邮件主题出错！");
		}
		try {
			// true 表示启动HTML格式的邮件
			messageHelper.setText(htmlContent, true);
		} catch (MessagingException e) {
			throw new Exception("邮件内容出错！");
		}

		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		// 超时时间
		prop.put("mail.smtp.timeout", "25000");

		// 添加验证
		MailAuthenticator auth = new MailAuthenticator(fileUtil.QueryValue("/configuration.properties","mail.username"), fileUtil.QueryValue("/configuration.properties","mail.pasword"));

		Session session = Session.getDefaultInstance(prop, auth);
		senderImpl.setSession(session);

		// 发送邮件
		senderImpl.send(mailMessage);
	}

	public static void main(String[] args) throws Exception {

		// 发送邮件
		// 主题
		String subject = "信息";
		// 正文
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		builder.append("</head><body>");
		builder.append("您好，张三：<br />");
		builder.append("\t系统已为您重置了RUI密码，账户信息如下：<br />");
		builder.append("用户账户：zhangsan<br />用户密码：123456<br />您可以点击以下链接登录RUI：");
		builder.append("<a href=\"");
		builder.append("\">");
		builder.append("</a>");
		builder.append("</body></html>");
		String htmlContent = builder.toString();

		MailUtil.sendMail("1041954045@qq.com", subject, htmlContent);
	}
	
	/**
	 * @Title: getHtmlContent 
	 * @Description: 为验证码封装html
	 * @param ret
	 * @return String     
	 * @throws
	 */
	public static String getHtmlContent(String ret){
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		builder.append("</head><body>");
		builder.append("发送的验证码为："+ret+"<br />");
		builder.append("</body></html>");
		String htmlContent = builder.toString();
		return htmlContent;
	}
	
}
