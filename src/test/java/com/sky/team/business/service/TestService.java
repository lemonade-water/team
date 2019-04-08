package com.sky.team.business.service;

import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.imp.UserServiceImp;
import com.sky.team.business.util.PageHelper;
import com.sun.mail.util.MailSSLSocketFactory;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    /*fdfd*/
    @Autowired
    private CourseService courseService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${IntelligenceIP}")
    private String IntelligenceIP;
    @Autowired
    private  UserService userService;

    @Autowired
    private CommentService commentService;
    @Test
    public void getCourseType(){
        HashMap<String, List<CourseType>> courseType = courseService.getCourseType();
        System.out.println(courseType);
    }


    @Test
    public void getCourse(){
        PageHelper pageHelper = new PageHelper();
        pageHelper.setPage(1);
        pageHelper.setQuery("");
        PageHelper allCourse = courseService.getAllCourse(pageHelper,"2450");
        System.out.println(allCourse);
    }


    /*课程推荐*/
    @Test
    public void setch(){
        courseService.getSketchClose("2");
    }

    /*测试添加课程*/
    @Test
    public void addCourse(){
        Course course = new Course();
        course.setcName("课程名字");
        course.setcTecBigType(1);
        course.setcTecSmallType(8);
        course.setcAuthor("课程作者");
        course.setcIntro("课程简介");
       // courseService.addCourse(course,"1");

    }
    
    @Test
    public void aaa() throws UnsupportedEncodingException {
        String url = IntelligenceIP+"/sketch/sensitive?txt="+"妈**的 哈哈";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        String outtxt = jsonObject.getString("outtxt");
        System.out.println(new String(outtxt.getBytes(),"UTF-8"));
    }

    @Test
    public void testGetEmail(){
        String userid="111";
        String username = "111";
        String userpassword = "111";
        String userEmail = "1107229735@qq.com";

        userService.getEmail(userid,username,userpassword,userEmail);
    }

    @Test
    public void testComment(){

        System.out.println(new Random().nextInt(8999) + 1000);
    }
    @Test
    public void email(){

        try {
            try {
                sendEmailMessage("1107229735@qq.com","123");
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendEmailMessage(String email,String code) throws MessagingException, GeneralSecurityException {
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.host", "smtp.163.com");
        //发送端口
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        MailSSLSocketFactory sf = null;
        sf = new MailSSLSocketFactory();
        // 设置信任所有的主机
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("13507087042@163.com","hds123456");
            }
        });
        session.setDebug(true);
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("13507087042@163.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
        //设置主题
        message.setSubject("这是一份测试邮件");
        //设置邮件正文  第二个参数是邮件发送的类型
        String text = "<p>您好,"+email+"<br><br> O(∩_∩)O~~欢迎使用微视频微分享平台<br>" +
                "<h2>验证码：</h2><h3>"+code+"</h3><br><p style='color:red'>请勿告诉他们，验证码在60s钟后将失效！<p></p>";
        message.setContent(text,"text/html;charset=UTF-8");
        //发送一封邮件

        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
