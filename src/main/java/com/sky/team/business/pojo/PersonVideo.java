package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class PersonVideo implements Serializable {
    /*用户上传视频主键*/
    private String personVideoId;
    /*用户上传视频名称*/
    private String personVideoName;
    /*标签*/
    private String personVideoTag;
    /*上传人*/
    private String personVideoUploader;
    /*视频时间*/
    private Date personVideoTime;
    /*上传人姓名*/
    private String personVideoUploadernName;
    /*视频介绍*/
    private String personVideoIntro;
    /*视频大小*/
    private float personVideoSize;
    /*视频状态*/
    private Integer personStatus;
    /*路径*/
    private String personVideoUrl;
    /*热度*/
    public Integer personVideoPop;

    public Integer getPersonVideoPop() {
        return personVideoPop;
    }

    public void setPersonVideoPop(Integer personVideoPop) {
        this.personVideoPop = personVideoPop;
    }

    public String getPersonVideoUrl() {
        return personVideoUrl;
    }

    public void setPersonVideoUrl(String personVideoUrl) {
        this.personVideoUrl = personVideoUrl;
    }

    public String getPersonVideoId() {
        return personVideoId;
    }

    public void setPersonVideoId(String personVideoId) {
        this.personVideoId = personVideoId;
    }

    public String getPersonVideoName() {
        return personVideoName;
    }

    public void setPersonVideoName(String personVideoName) {
        this.personVideoName = personVideoName;
    }

    public String getPersonVideoTag() {
        return personVideoTag;
    }

    public void setPersonVideoTag(String personVideoTag) {
        this.personVideoTag = personVideoTag;
    }

    public String getPersonVideoUploader() {
        return personVideoUploader;
    }

    public void setPersonVideoUploader(String personVideoUploader) {
        this.personVideoUploader = personVideoUploader;
    }

    public Date getPersonVideoTime() {
        return personVideoTime;
    }

    public void setPersonVideoTime(Date personVideoTime) {
        this.personVideoTime = personVideoTime;
    }

    public String getPersonVideoUploadernName() {
        return personVideoUploadernName;
    }

    public void setPersonVideoUploadernName(String personVideoUploadernName) {
        this.personVideoUploadernName = personVideoUploadernName;
    }

    public String getPersonVideoIntro() {
        return personVideoIntro;
    }

    public void setPersonVideoIntro(String personVideoIntro) {
        this.personVideoIntro = personVideoIntro;
    }

    public float getPersonVideoSize() {
        return personVideoSize;
    }

    public void setPersonVideoSize(float personVideoSize) {
        this.personVideoSize = personVideoSize;
    }

    public Integer getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(Integer personStatus) {
        this.personStatus = personStatus;
    }
}
