package site.euan.bester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import site.euan.bester.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String toEmail, String code) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(new InternetAddress("507327431@qq.com", "爱聊团队"));

        helper.setTo(toEmail);
        helper.setSubject("验证码通知");
//        String html = "<img src='cid:avatar' width='50'> <p>你的验证码为：" + code + "</p>";
//        helper.setText(html, true);
//        helper.addInline("avatar", new ClassPathResource("images/avatar.jpg"));
        helper.setText("你的验证码为：" + code + "，5分钟内有效");
        mailSender.send(message);
    }
}
