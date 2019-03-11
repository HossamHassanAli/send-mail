package com.z2data.sourcing.email.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.z2data.sourcing.email.bean.MailFeatures;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(MailFeatures mailFeatures) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			String emailTo = mailFeatures.getTo();
			String emailCC = mailFeatures.getCc();
			String emailFrom = mailFeatures.getFrom();
			String emailAtt = mailFeatures.getAttachFilepath();

			List<File> files = new ArrayList<>();

			// handle multi CC recipient
			emailTo = mailFeatures.getTo();
			InternetAddress[] parseTO = InternetAddress.parse(emailTo, true);
			message.setRecipients(javax.mail.Message.RecipientType.TO, parseTO);

			// handle multi TO recipient
			emailCC = mailFeatures.getCc();
			InternetAddress[] parseCC = InternetAddress.parse(emailCC, true);
			message.setRecipients(javax.mail.Message.RecipientType.CC, parseCC);

			helper.setSubject(mailFeatures.getSubject());
			helper.setText(mailFeatures.getMailContent());
			helper.setFrom(emailFrom);
			// handle multi attachment
			if (!(emailAtt.isEmpty())) {
				String[] attachmentsPaths = emailAtt.split(",");

				// add the file attachment
				for (int i = 0; i < attachmentsPaths.length; i++) {
					File attachment = new File(attachmentsPaths[i]);
					files.add(attachment);
				}

				for (File file : files) {
					FileSystemResource fr = new FileSystemResource(file);
					helper.addAttachment(file.getName(), fr);
				}
			}
			

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}

		javaMailSender.send(message);

	}
}

//SimpleMailMessage message = new SimpleMailMessage();
//message.setTo(mailFeatures.getTo());
//message.setSubject(mailFeatures.getSubject());
//message.setText(mailFeatures.getMailContent());

//FileSystemResource file = new FileSystemResource(mailFeatures.getAttachFilepath());
//helper.addAttachment(file.getFilename(), file);
