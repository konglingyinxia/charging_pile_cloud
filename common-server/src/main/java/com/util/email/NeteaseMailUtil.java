package com.util.email;

import com.util.DealDateUtil;
import com.util.PropertiesUtil;
import com.util.request.HttpRequestUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * 网易 163邮箱工具类
 *
 * @author 卫星
 * @package com.utils.email
 * @date 2018-09-11  09:27
 * @project 3iex
 */

public class NeteaseMailUtil {

    public static String sendEmail = PropertiesUtil.readProperties("sendEmail");
    public static String emailHost = PropertiesUtil.readProperties("emailHost");
    public static String emailUsername = PropertiesUtil.readProperties("emailUsername");
    public static String authorizationPwd = PropertiesUtil.readProperties("authorizationPwd");
    public static String isAuth = PropertiesUtil.readProperties("isAuth");
    public static String linkTimeout = PropertiesUtil.readProperties("linkTimeout");

    public static String title = PropertiesUtil.readProperties("title");
    public static String text = PropertiesUtil.readProperties("text");

    public static boolean sendMail(String to, String title, String text) {
        boolean isResult = sendMailHtml(to, title, text);
        return isResult;
    }


    /**
     * @param to    目的邮箱
     * @param title 标题
     * @param text  内容
     * @return
     */
    private static boolean sendMailText(String to, String title, String text) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Properties prop = new Properties();
        System.out.println("sendMail...util...");
        try {
            //设定mail server
            senderImpl.setHost(emailHost);
            //senderImpl.setPort(587);

            // 设置收件人，寄件人 用数组发送多个邮件
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);
            mailMessage.setTo(to);
            mailMessage.setFrom(sendEmail);
            mailMessage.setSubject(title);
            mailMessage.setText(text);

            senderImpl.setUsername(emailUsername);
            senderImpl.setPassword(authorizationPwd);

            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            prop.setProperty("mail.smtp.socketFactory.fallback", "false");

            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");

            // 设置传输协议
            prop.setProperty("mail.transport.protocol", "smtp");

            prop.put("mail.smtp.auth", isAuth);
            prop.put("mail.smtp.timeout", linkTimeout);
            senderImpl.setJavaMailProperties(prop);
            System.out.println("发送信息：" + text);

            MimeMessage msg = senderImpl.createMimeMessage();
            msg.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(sendEmail));
            //发送邮件
            senderImpl.send(mailMessage);

            System.out.println("发送邮件成功");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件失败");
            return false;
        }
    }

    public static void main(String[] args) {
        sendMailHtml("hedankly@foxmail.com", title, String.format(NeteaseMailUtil.text, "1111"));
    }

    /**
     * 发送网页图片邮箱
     *
     * @param to
     * @param title
     * @param text
     * @return
     */
    private static boolean sendMailHtml(String to, String title, String text) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        //SimpleMailMessage mailMessage = new SimpleMailMessage();

        //建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();

        Properties prop = new Properties();
        System.out.println("sendMail...util...");
        try {
            //注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用,
            //multipart模式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            prop.setProperty("mail.smtp.socketFactory.fallback", "false");

            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");

            // 设置传输协议
            prop.setProperty("mail.transport.protocol", "smtp");

            prop.put("mail.smtp.auth", isAuth);
            prop.put("mail.smtp.timeout", linkTimeout);
            senderImpl.setJavaMailProperties(prop);
            senderImpl.setUsername(emailUsername);
            senderImpl.setPassword(authorizationPwd);
            //设定mail server
            senderImpl.setHost(emailHost);
            //senderImpl.setPort(587);

            // 设置收件人，寄件人 用数组发送多个邮件
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);
            messageHelper.setTo(to);
            messageHelper.setFrom(sendEmail);
            messageHelper.setSubject(title);
            messageHelper.setText(messageHtml(text), true);

            System.out.println("发送信息：" + text);

            MimeMessage msg = senderImpl.createMimeMessage();
            msg.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(sendEmail));
            //发送邮件
            senderImpl.send(mailMessage);

            System.out.println("发送邮件成功");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件失败");
            return false;
        }
    }

    /**
     * 构造网页
     *
     * @param msg
     * @return
     */
    private static String messageHtml(String msg) {
        String imgUrl = HttpRequestUtil.getReqHttpAndHttpsPath() + "/email/email_home_img.png";
        //String imgUrl = "http://centbtc.com/email/email_home_img.png";

        String html = "<html> <head> <title>Centsbtc</title> <meta charset=\"utf-8\"/> <style type=\"text/css\">" +
                "            body{" +
                // "               text-align:center" +
                "            }" +
                "            .center_set{" +
                "                margin:0 auto;" +
                "                width:600px;" +
                "                height:100px;" +
                "            }" +
                "            .content{" +
                "                width: 600px;" +
                //"                height: 3px;" +
                "            }" +
                "            .header,img{" +
                "              width: 600px;" +
                "             }" +
                "            .footer{" +
                "                text-align: right;" +
                "            }" +
                "            #name{" +
                "                color: grey;" +
                "            }" +
                "            #message{" +
                "                text-align: left;" +
                "                width: 600px;" +
                "                color: grey;" +
                "            }" +
                "        </style> </head> <body><div class=\"center_set\"> <div class=\"header\"> <img src=\"" + imgUrl + "\"/> </div> " +
                "<div class=\"content\"> <div id=\"message\">" + msg +
                "</div> " +
                "<div class=\"footer\"><br/>日期:<span id=\"time\">" + DealDateUtil.dateToString(new Date()) + "</span></div> </div></div> </body> </html>";

        return html;
    }


}

