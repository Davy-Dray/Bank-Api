package com.ragnar.bankapp.BankApi.service.implementation;

import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.DEFAULT_PORT;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.EMAIL_SMTP_SERVER;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.SMTP_AUTH;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.SMTP_HOST;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.SMTP_PORT;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.SMTP_STARTTLS_ENABLE;
import static com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant.SMTP_STARTTLS_REQUIRED;

import java.util.Date;
import java.util.Properties;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.ragnar.bankapp.BankApi.EmailConstant.EmailConstant;

@Service
public class EmailService {

	public void sendMail(String firstName, String accountId, String email) throws AddressException, MessagingException {

		Message message = createMail(firstName, accountId, email);

		SMTPTransport transport = (SMTPTransport) getEmailSession()
				.getTransport(EmailConstant.SIMPLE_MAIL_TRANSFER_PROTOCOL);

		transport.connect(EmailConstant.EMAIL_SMTP_SERVER, EmailConstant.USERNAME, EmailConstant.PASSWORD);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();

	}

	private Session getEmailSession() {

		Properties properties = System.getProperties();

		properties.put(SMTP_HOST, EMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);

		return Session.getInstance(properties, null);

	}

	private Message createMail(String firstName, String accountId, String email)
			throws AddressException, MessagingException {

		Message message = new MimeMessage(getEmailSession());

		message.setFrom(new InternetAddress(EmailConstant.FROM_EMAIL));

		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

	//	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailConstant.CC_MAIL, false));

		message.setSubject(EmailConstant.EMAIL_SUBJECT);

		message.setText("hello motherfucker " + firstName + " ,\n \n Your account number " + accountId + " \n \n Yours \n My Bank");

		message.setSentDate(new Date());

		message.saveChanges();
		return message;

	}
}
