package com.sky.team.business.service.imp;


import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.dao.UserDao;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    private UserDao userDao;

    @Value("${host.email}")
    private String hostemail;

    @Value("${host.pwd}")
    private String hostpwd;

    @Autowired()  // @Resource
    private CourseDao courseDao;

    @Override
    public Boolean login(String userid, String userpassword, HttpServletRequest request) {
        try{
            User user = userDao.getUser(userid);
            /*验证密码*/
            if(user.getUserPassword().equals(userpassword)&&user.getUserId().equals(userid)){
                HttpSession session = request.getSession(true);
                session.setAttribute("principal",user);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean isUsername(String username) {
        User user = userDao.getUser(username);
        if(user==null){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(User user) {

        /*验证邮箱*/
        User register_user = userDao.selectRegister(user.getUserId(), user.getUserEmail());
        if(register_user!=null&&register_user.getUserEmailCode().equals(user.getUserEmailCode())){
            Date userLastTime = register_user.getUserLastTime();
            if((new Date().getTime()-userLastTime.getTime())>=150000l){
                return false;
            }
            String hashAlgorithmName = "MD5";
            String credentials = user.getUserPassword();
            int hashIterations = 1024;
            Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
            /*插入数据*/
            userDao.addUser(user.getUserId(),user.getUserName(),obj.toString(),user.getUserEmail(),user.getUserEmailCode(),user.getUserDepartment());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void getEmail(String userid,String username, String userpassword, String useremail) {
        /*生成一个code*/

        String code =UUID.randomUUID().toString().replaceAll("-","").substring(0,6);

        /*王数据库里面插入数据*/


        /*发邮箱*/
        Executor executor =Executors.newCachedThreadPool();
        executor.execute(()->{
            sendEmail(useremail,code);
            userDao.registerUser(userid,username,userpassword,useremail,code,new Date());
        });

    }

    /*发邮箱方法*/
    public void sendEmail(String email,String code){
        Date date = new Date();
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName("smtp.163.com");
        try {
            htmlEmail.addTo(email);
            htmlEmail.setCharset("utf-8");
            htmlEmail.setFrom(hostemail,"擎天科技有限公司");
            htmlEmail.setAuthentication(hostemail,hostpwd);
            htmlEmail.setSubject("微视频微分享注册信息");
            String msg = "<p>您好,"+email+"<br><br> O(∩_∩)O~~欢迎使用微视频微分享平台<br>" +
                    "<h2>验证码：</h2><h3>"+code+"</h3><br><p style='color:red'>请勿告诉他们，验证码在60s钟后将失效！<p></p>";
            htmlEmail.setMsg(msg);
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        Date date1 = new Date();
        System.out.println((date1.getTime()-date.getTime()));
    }
}
