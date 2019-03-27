package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChapterCourse implements Serializable {

    private String chapterId;
    private String cId;
    private String chUrl;
    private String chIntro;
    private Date chTime;
    private String delflag;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getChUrl() {
        return chUrl;
    }

    public void setChUrl(String chUrl) {
        this.chUrl = chUrl;
    }

    public String getChIntro() {
        return chIntro;
    }

    public void setChIntro(String chIntro) {
        this.chIntro = chIntro;
    }

    public Date getChTime() {
        return chTime;
    }

    public void setChTime(Date chTime) {
        this.chTime = chTime;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }
}
