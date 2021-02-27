package com.osweld.dev.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class JavaMailServiceImpl implements JavaMailService{

    @Autowired
    private JavaMailSender javaMail;

    @Value(value = "${spring.mail.username}")
    private String from;

    @Value(value = "${com.oswelddev.url}")
    private String sendEmailUrl;

    @Override
    public void SendMimeMessageActivation(String to,String id) {

        String text = "Para activar la cuenta de click en el siguiente enlace:";
        String linkText = "Activar cuenta";

        MimeMessage mimeMessage = javaMail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);


        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Activar Cuenta");
            helper.setText(HTMLTemplate(id,text,linkText),true);
            javaMail.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
//    @Async
    @Override
    public void SendMimeMessageResetPassword(String to,String id) {

        String text = "Para cambiar la contraseña de click en el siguiente enlace:";
        String linkText = "Cambiar contraseña";

        MimeMessage mimeMessage = javaMail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Cambio de contraseña");
            helper.setText(HTMLTemplate(id,text,linkText),true);
            javaMail.send(mimeMessage);
        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    private String HTMLTemplate(String id,String text,String linkText){
        return "<h1>NotasApp</h1>"+
                "<p> "+text+" </p>"+
                "<p> "+id+" </p>"+
                "<a href=\""+sendEmailUrl+"/token/"+id+"\">"+linkText+"</a>";
    }



}
