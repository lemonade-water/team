package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class Collection implements Serializable {
    private String cId;
    private String cName;
    private String cIntro;
    private String cPictureUrl;
    private String userId;
    private String userName;
    private Date cUploadTime;

    public Date getcUploadTime() {
        return cUploadTime;
    }

    public void setcUploadTime(Date cUploadTime) {
        this.cUploadTime = cUploadTime;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcIntro() {
        return cIntro;
    }

    public void setcIntro(String cIntro) {
        this.cIntro = cIntro;
    }

    public String getcPictureUrl() {
        return cPictureUrl;
    }

    public void setcPictureUrl(String cPictureUrl) {
        this.cPictureUrl = cPictureUrl;
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
}
