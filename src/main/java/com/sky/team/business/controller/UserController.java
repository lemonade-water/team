package com.sky.team.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.UserDao;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.UserService;
import com.sky.team.business.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    /*每个方法对应


    servlet - handle

    @RequestMapping   /api/protected-->handle
    */
    @RequestMapping("/api/protected")
    public @ResponseBody String hellWorld(){
        return "Hello World! This is a protected api";
    }


    /*
       request
    * @RequestParam ?username=22&userpassword=11112
    * @RequestBody  String params
    * Course course
    *
    * response
    * REST
    *
    *
    * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user, HttpServletRequest request){

//        JSONObject jsonObject = JSONObject.parseObject(formStr);
//        String userid = jsonObject.getString("userId");
//        String userpassword = jsonObject.getString("userPassword");

        /*验证密码是否正确*/
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserId(), user.getUserPassword());
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(usernamePasswordToken);
            userService.updateLoginTime((String)subject.getPrincipal(),new Date());
            String jwt = JwtUtil.generateToken(user.getUserId());
            Session session = subject.getSession();
            User user1 = userDao.getUser(user.getUserId());
            session.setAttribute("user",user1);
            return new HashMap<String,String>(){{
                put("token", jwt);
            }};
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/api/autoLogin",method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try{
            String token = request.getHeader("Authorization");
            //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
            JwtUtil.validateToken(token);
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

    }



    /*获得邮箱的验证*/
    @RequestMapping("/getEmail")
    public Boolean getEmail(@RequestBody User user,
                            HttpServletRequest request){
        /*验证邮箱是否正确*/
        if(user.getUserEmail().contains("@")&&user.getUserId()!=null){
            userService.getEmail(user.getUserId(),user.getUserName(),user.getUserPassword(),user.getUserEmail());
            return true;

        } return false;

    }

    @RequestMapping("/getEmail1")
    public Boolean getEmail1(@RequestParam("userid")String userid,@RequestParam("useremail")String useremail,
                            HttpServletRequest request){
        /*验证邮箱是否正确*/
        if(useremail.contains("@")&&userid!=null){
            userService.getEmail(userid,null,null,useremail);
           return true;

        } return false;

    }



    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody User user,
                           HttpServletRequest request){
        /*验证账号密码是否符合规则*/

        if(isValidate(user.getUserId(),user.getUserPassword(),user.getUserEmail())){
            return userService.register(user);
        }else {
            return null;
        }

    }

    @RequestMapping(value = "/isUserId",method = RequestMethod.GET)
    @ResponseBody
    public Integer isUsername(@RequestParam(name = "userId")String username){

        /*验证username*/
        if(username==null||username.length() != username.getBytes().length){
            return 3;
        }

        /*查询数据库是否有该用户*/

        if(userService.isUsername(username)){
            return 1;
        }else {
            return 2;
        }
    }

//    验证
    public boolean isValidate(String username,String userpassword,String email){
        if(username==null||userpassword==null||email==null){
            return false;
        }else{
            if(username.length() != username.getBytes().length||userpassword.length() != userpassword.getBytes().length||email.length() != email.getBytes().length){
                return false;
            }else{
                if(email.contains("@")){
                    return true;
                }else {
                    return false;
                }
            }
        }

    }

    /*修改密码*/
    @RequestMapping("/api/updatePassword")
    public ResultMessage updatepassword(@RequestBody User user){
        if(user.getUserId()==null&&user.getUserEmail()==null){
            return ResultMessage.setResultMessage("404","账号或者邮箱不能为空");
        }else{
            /*跟改密码*/
            User user1 = userDao.selectRegister(user.getUserId(), user.getUserEmail());
            if(user1!=null&&user1.getUserEmailCode().equals(user.getUserEmailCode())){
                Date userLastTime = user1.getUserLastTime();
                if((new Date().getTime()-userLastTime.getTime())>=150000l){
                    return ResultMessage.setResultMessage("402","邮箱验证超时");
                }
                String hashAlgorithmName = "MD5";
                String credentials = user.getUserPassword();
                int hashIterations = 1024;
                Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
                /*插入数据*/
                user.setUserPassword(obj.toString());
                /*根据用户*/
                userDao.updatePassword(user);
            }

        }
        return null;
    }

    /*修改密码的时候邮箱*/
    @RequestMapping("/api/updateGetEmail")
    public ResultMessage getEmail(@RequestBody User user){
        try{
            String principal = (String)SecurityUtils.getSubject().getPrincipal();

            if(principal==null){
                user.setUserId(user.getUserId());
            }else{
                user.setUserId  (principal);
            }
        }catch (Exception e){
            user.setUserId(user.getUserId());
        }

        if(user.getUserEmail()==null){
            return ResultMessage.setResultMessage("404","邮箱不能为空");
        }else{
            userService.updateGetEmail(user);
            return ResultMessage.setResultMessage("200","成功");
        }
    }
}
