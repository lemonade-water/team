package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;



/*历史纪录*/
public class UCourse implements Serializable {
    private String uCId;
    private String cId;
    private String uId;
    private String chapterId;
    private Date userlearntime;
    private Date coursetime;
    private String subsectionId;
    private Date updatetime;

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getuCId() {
        return uCId;
    }

    public void setuCId(String uCId) {
        this.uCId = uCId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public Date getUserlearntime() {
        return userlearntime;
    }

    public void setUserlearntime(Date userlearntime) {
        this.userlearntime = userlearntime;
    }

    public Date getCoursetime() {
        return coursetime;
    }

    public void setCoursetime(Date coursetime) {
        this.coursetime = coursetime;
    }

    public String getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(String subsectionId) {
        this.subsectionId = subsectionId;
    }
}
