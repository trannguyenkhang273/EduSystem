/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
 
//import com.gpcoder.MailConfig;
/**
 *
 * @author hi
 */ 
public class Email1 {
    public static void main(String[] args) throws EmailException, MalformedURLException {
 
        // Tạo đối tượng Email
        ImageHtmlEmail email = new ImageHtmlEmail();
 
        // Cấu hình thông tin Email Server
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("trannguyenkhang147@gmail.com", "01627987350a"));
        email.setSSLOnConnect(true);
 
        // Người gửi
        email.setFrom("trannguyenkhang147@gmail.com");
 
        // Người nhận
        email.addTo("trannguyenkhang273@gmail.com");
 
        // Tiêu đề
        email.setSubject("Testing Subject");
        File sourceimage = new File("D:\\Images\\logoMOI1.png");
        
        // Định nghĩa URL cơ sở để xác định đúng vị trí nguồn dữ liệu (img,..)
        // (Trong trường hợp nó có đường dẫn tương đối, ví dụ thẻ img như bên dưới)
//        URL url = new URL("https://gpcoder.com");
//        URL url = new URL("https://lambanner.com/wp-content/uploads/2020/04/MNT-DESIGN-10-MEO-THIET-KE-LOGO.jpg");
//        String cid = email.embed(url, "Apache logo");
//        email.setDataSourceResolver(new DataSourceUrlResolver(url));
// "<h1>Welcome to <a href=\"gpcoder.com\">GP Coder</a></h1>"
//                +
        // Nội dung email
//        String htmlContent =  "<img src=\"wp-content/uploads/2017/10/Facebook_Icon_GP_2-300x180.png\"" + " width=\"300\" " + " height=\"180\" " + " border=\"0\" " + " alt=\"gpcoder.com\" />";
        String htmlContent="<div class=\"asd\" style=\"background-color: lightgray; width: 100%; height: 400px; padding-top: 100px;padding-left: 100px; \"> \n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"        <h2>aslkdjaklsdjl</h2>\n" +
"    </div>\n";
        email.setHtmlMsg(htmlContent);
 
        // Nội dung thay thế:
        // Trong trường hợp chương trình đọc email của người nhận ko hỗ trợ HTML
        email.setTextMsg("Your email client does not support HTML messages");
 
        // send message
        email.send();
 
        System.out.println("Message sent successfully");
    }
}
