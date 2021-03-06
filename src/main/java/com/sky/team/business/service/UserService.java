package com.sky.team.business.service;

import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.pojo.User;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface UserService {
    Boolean login(String username, String userpassword, HttpServletRequest request);
    Boolean isUsername(String username);
    boolean register(User user);
    void updateLoginTime(String userid,Date date);
    void getEmail(String userid,String usernmae, String userpassword, String useremail);

    ResultMessage updateGetEmail(User user);

    ResultMessage updateUser(User user);
}
