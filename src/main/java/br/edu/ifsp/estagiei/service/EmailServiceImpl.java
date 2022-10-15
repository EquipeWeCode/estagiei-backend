package br.edu.ifsp.estagiei.service;

import java.io.File;
import java.net.URL;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.config.GlobalControllerAdvice;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
	private static final String ATTACH_URL = "https://res.cloudinary.com/dlkvupjc7/image/upload/v1665797374/email-attach_ryiqfi.png";

	public void sendSimpleMessage(String to, String subject, String text) {
		try {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("wecodetrabalho@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
			logger.info("E-mail enviado com sucesso para: " + to);
		} catch (Exception e) {
			logger.error("Erro ao tentar enviar o e-mail: ", e);
		}
	}

	public void sendMailWithAttachment(String to, String subject, String text) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom("wecodetrabalho@gmail.com");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(text);
			mimeMessageHelper.setSubject(subject);

			URL url = new URL(ATTACH_URL);

			File file = new File("bannerEstagiei.png");
			FileUtils.copyURLToFile(url, file);

			FileSystemResource fileSystem = new FileSystemResource(file);

			mimeMessageHelper.addAttachment(fileSystem.getFilename(), file);
			emailSender.send(mimeMessage);
			logger.info("E-mail enviado com sucesso para: " + to);
		}

		catch (Exception e) {
			logger.error("Erro ao tentar enviar o e-mail: ", e);
		}
	}
}