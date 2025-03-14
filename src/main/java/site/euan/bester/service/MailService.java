package site.euan.bester.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {
    void sendVerificationCode(String toEmail,String code) throws MessagingException, UnsupportedEncodingException;
}
