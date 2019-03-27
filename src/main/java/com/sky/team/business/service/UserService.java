package com.sky.team.business.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Boolean login(String username, String userpassword, HttpServletRequest request);
    Boolean isUsername(String username);
    boolean register(String userid,String usernmae, String userpassword, String useremail,String userdepartment,String emailCode);

    void getEmail(String userid,String usernmae, String userpassword, String useremail);
}
