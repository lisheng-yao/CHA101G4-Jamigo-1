package com.jamigo.member.member_data.util;

import com.jamigo.member.member_data.Service.MemberService;
import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class SendEmail4forgot {
    @Autowired
    private MemberService memberService;


    public boolean sendMail(MemberData memberData) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            final String myGmail = "jamigo.contact@gmail.com";
            final String myGmail_password = "xtnmxghelweuykcs";
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myGmail, myGmail_password);
                }
            });

            String password4forgot = memberService.forgot(memberData);
            if (password4forgot != null) {
                String email = memberData.getMemberEmail();
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myGmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Jamigo忘記密碼");//標題
                message.setText("這是您的密碼 ：" + password4forgot);//內容

                Transport.send(message);
//			mailSender.send(message);
                System.out.println("傳送成功!");
                return true;
            } else {
                return false;
            }
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
            return false;
        }
    }

}
