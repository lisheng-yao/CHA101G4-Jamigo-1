package com.jamigo.member.member_data.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jamigo.member.member_data.util.CommonUtil2;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {


    public void sendMail(String email) {

        try {
            CommonUtil2 redisutil = new CommonUtil2();
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            final String myGmail = "jamigo.contact@gmail.com";
            final String myGmail_password = "espqaxcqcwymhpli";
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myGmail, myGmail_password);
                }
            });

            String randoncode4email = redisutil.RandonCode();
            redisutil.saveRandonCode(email, randoncode4email);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myGmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("註冊驗證碼");//標題
            message.setText("感謝您的註冊，以下是您的驗證碼，請在兩分鐘內輸入。" + randoncode4email);//內容

            Transport.send(message);
//			mailSender.send(message);
            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

}
