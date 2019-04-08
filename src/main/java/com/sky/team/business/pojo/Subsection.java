package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;
public class Subsection implements Serializable {

    private String subsectionId;
    private String subsectionName;
    private String chapterId;
    private String videoUrl;
    private Date  videoTime;
    private String videoFormat;
    private String videoName;
    private float videoSize;
    private String  subsectionCId;

    public String getSubsectionCId() {
        return subsectionCId;
    }

    public void setSubsectionCId(String subsectionCId) {
        this.subsectionCId = subsectionCId;
    }

    public String getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(String subsectionId) {
        this.subsectionId = subsectionId;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public float getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(float videoSize) {
        this.videoSize = videoSize;
    }
}
