package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    private String cId;
    private String cName;
    private String cTecBigType;
    private String cTecSmallType;
    private String cIntro;
    private String cAuthor;
    private String cColumnIntro;
    private String delflag;
    private Integer cChapterCount;
    private Integer cCollection;
    private Date cUploadTime;
    private String cUploader;
    private String cPictureUrl;
    private String cType;

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

    public String getcTecBigType() {
        return cTecBigType;
    }

    public void setcTecBigType(String cTecBigType) {
        this.cTecBigType = cTecBigType;
    }

    public String getcTecSmallType() {
        return cTecSmallType;
    }

    public void setcTecSmallType(String cTecSmallType) {
        this.cTecSmallType = cTecSmallType;
    }

    public String getcIntro() {
        return cIntro;
    }

    public void setcIntro(String cIntro) {
        this.cIntro = cIntro;
    }

    public String getcAuthor() {
        return cAuthor;
    }

    public void setcAuthor(String cAuthor) {
        this.cAuthor = cAuthor;
    }

    public String getcColumnIntro() {
        return cColumnIntro;
    }

    public void setcColumnIntro(String cColumnIntro) {
        this.cColumnIntro = cColumnIntro;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Integer getcChapterCount() {
        return cChapterCount;
    }

    public void setcChapterCount(Integer cChapterCount) {
        this.cChapterCount = cChapterCount;
    }

    public Integer getcCollection() {
        return cCollection;
    }

    public void setcCollection(Integer cCollection) {
        this.cCollection = cCollection;
    }

    public Date getcUploadTime() {
        return cUploadTime;
    }

    public void setcUploadTime(Date cUploadTime) {
        this.cUploadTime = cUploadTime;
    }

    public String getcUploader() {
        return cUploader;
    }

    public void setcUploader(String cUploader) {
        this.cUploader = cUploader;
    }

    public String getcPictureUrl() {
        return cPictureUrl;
    }

    public void setcPictureUrl(String cPictureUrl) {
        this.cPictureUrl = cPictureUrl;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }
}
