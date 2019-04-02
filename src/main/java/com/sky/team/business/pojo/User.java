package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String userId;
    private String userName;
    private String userPassword;
    private Date userLastTime;
    private String userEmail;
    private String userEmailCode;
    /*用户积分*/
    private Integer userIntegrate;
    private String delflag;
    private String userDepartment;
    private Date userLearnTime;
    private Role role;

    public Date getUserLearnTime() {
        return userLearnTime;
    }

    public void setUserLearnTime(Date userLearnTime) {
        this.userLearnTime = userLearnTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserLastTime() {
        return userLastTime;
    }

    public void setUserLastTime(Date userLastTime) {
        this.userLastTime = userLastTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmailCode() {
        return userEmailCode;
    }

    public void setUserEmailCode(String userEmailCode) {
        this.userEmailCode = userEmailCode;
    }

    public Integer getUserIntegrate() {
        return userIntegrate;
    }

    public void setUserIntegrate(Integer userIntegrate) {
        this.userIntegrate = userIntegrate;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
