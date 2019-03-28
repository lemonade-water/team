package com.sky.team.business.service;

import com.sky.team.business.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface UserService {
    Boolean login(String username, String userpassword, HttpServletRequest request);
    Boolean isUsername(String username);
    boolean register(User user);
    void updateLoginTime(String userid,Date date);
    void getEmail(String userid,String usernmae, String userpassword, String useremail);
}
