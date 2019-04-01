package com.sky.team.business.service.imp;

import com.sky.team.business.dao.UserDao;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MyThread extends Thread {
    private String userId;
    private String userEmial;
    private String code;
    private String hostemail;
    private String hostpwd;
    public MyThread(){}
    public MyThread(String userId,String userEmial,String code,String hostemail,String hostpwd){
        this.userId=userId;
        this.userEmial=userEmial;
        this.code=code;
        this.hostemail = hostemail;
        this.hostpwd = hostpwd;
    }
    @Override
    public void run() {
        Date date = new Date();
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName("smtp.163.com");
        try {
            htmlEmail.addTo(this.userEmial);
            htmlEmail.setCharset("utf-8");
            htmlEmail.setFrom(this.hostemail,"擎天科技有限公司");
            htmlEmail.setAuthentication(this.hostemail,this.hostpwd);
            htmlEmail.setSubject("微视频微分享注册信息");
            String msg = "<p>您好,"+this.userEmial+"<br><br> O(∩_∩)O~~欢迎使用微视频微分享平台<br>" +
                    "<h2>验证码：</h2><h3>"+this.code+"</h3><br><p style='color:red'>请勿告诉他们，验证码在60s钟后将失效！<p></p>";
            htmlEmail.setMsg(msg);
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        Date date1 = new Date();
        System.out.println((date1.getTime()-date.getTime()));

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmial() {
        return userEmial;
    }

    public void setUserEmial(String userEmial) {
        this.userEmial = userEmial;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHostemail() {
        return hostemail;
    }

    public void setHostemail(String hostemail) {
        this.hostemail = hostemail;
    }

    public String getHostpwd() {
        return hostpwd;
    }

    public void setHostpwd(String hostpwd) {
        this.hostpwd = hostpwd;
    }
}
