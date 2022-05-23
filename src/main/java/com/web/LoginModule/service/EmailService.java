package com.web.LoginModule.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.account}")
    private String account;

    @Value("${email.password}")
    private String password;
    //host类型
    public static final String HOST_QQ = "qq";
    public static final String HOST_163 = "163";
    public static final String HOST_GMAIL = "gmail";
    public static final String HOST_EXMAIL = "exmail";
    //消息类型
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_FILE = "file";

    public void sendMailFromQQ(String hostType,String messageType,List<String> target,String subject,String content,ByteArrayOutputStream outputStream){
        String EmailSMTPHost = getHostByType(hostType);
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", EmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        generateAndSendMessage(props,messageType, target, subject, content,outputStream);
//        sendMessageByType(messageType,target,subject,content,outputStream);
    }

    public String getHostByType(String hostType){
        String host = "";
        switch (hostType){
            case "qq":
                host = "smtp.qq.com";
                break;
            case "exmail":
                host = "smtp.exmail.qq.com";
                break;
            case "163":
                host = "smtp.163.com";
                break;
            case "gmail":
                host = "smtp.gmail.com";
                break;
            default:
                break;
        }
        return host;
    }

    public MimeMessage getMessageByType(Session session,String messageType,List<String> target,String subject,String content,ByteArrayOutputStream outputStream) throws Exception {
        MimeMessage message = null;
        switch (messageType){
            case "text":
                message = createDefaultMessage(session, account, target,subject,content);
                break;
            case "image":
                message = createImageMessage(session, account, target,subject,content,outputStream);
                break;
            case "file":
                break;
            default:
                break;
        }
        return message;
    }

    public void generateAndSendMessage(Properties props,String messageType,List<String> target,String subject,String content,ByteArrayOutputStream outputStream){
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        Transport transport = null;
        try {
            // 3. 创建一封邮件
//			MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,subject,contont);
//            MimeMessage message = createImageMessage(session, source, target,subject,content);
            MimeMessage message = getMessageByType(session,messageType,target,subject,content, outputStream);
            // 4. 根据 Session 获取邮件传输对象
            transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            transport.connect(account, password);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(transport!=null)
            {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public  MimeMessage createDefaultMessage(Session session, String sendMail, List<String> receiveMail,String subject,String contont) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//        message.setFrom(new InternetAddress(sendMail, "河北省招标投标公共服务平台", "UTF-8"));
        message.setFrom(new InternetAddress(sendMail, "中国电子保函网", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        List<Address> list = new ArrayList<>();
        for (String receive:receiveMail){
            list.add(new InternetAddress(receive,"zbh", "UTF-8"));
        }
        message.setRecipients(MimeMessage.RecipientType.TO, list.toArray(new InternetAddress[list.size()]));//收件人地址
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(contont, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    /**
     * 创建一封包含文本、超链接、图片的邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public  MimeMessage createImageMessage(Session session, String sendMail, List<String> receiveMail,String subject,String content,ByteArrayOutputStream outputStream) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//        message.setFrom(new InternetAddress(sendMail, "河北省招标投标公共服务平台", "UTF-8"));
        message.setFrom(new InternetAddress(sendMail, "中国电子保函网", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        List<Address> list = new ArrayList<>();
        for (String receive:receiveMail){
            list.add(new InternetAddress(receive,"zbh", "UTF-8"));
        }
        //设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
        message.setRecipients(MimeMessage.RecipientType.TO, list.toArray(new InternetAddress[list.size()]));//收件人地址
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
//        message.setContent(content, "text/html;charset=UTF-8");
        MimeMultipart multipart = new MimeMultipart();
        //读取本地图片,将图片数据添加到"节点"
        MimeBodyPart image = new MimeBodyPart();
//        ByteArrayOutputStream outputStream = generateQRCodeImage("hello,it's a test", 300, 300);
        DataSource aAttachment = new ByteArrayDataSource(outputStream.toByteArray(), "application/octet-stream");
        image.setDataHandler(new DataHandler(aAttachment));
//        image.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Administrator/Documents/image.jpg")));
        image.setContentID("image");
        //创建文本节点
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content,"text/html;charset=UTF-8");
        //将文本和图片添加到multipart
        multipart.addBodyPart(text);
        multipart.addBodyPart(image);
        multipart.setSubType("related");
        message.setContent(multipart,"text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

//    public static ByteArrayOutputStream generateQRCodeImage(String text, int width, int height)
//            throws WriterException, IOException {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
////        Path path = FileSystems.getDefault().getPath(filePath);
////        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", outputStream);
//        return outputStream;
//    }

}
