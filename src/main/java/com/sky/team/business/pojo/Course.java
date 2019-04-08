package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Course implements Serializable {
    private String cId;
    private String cName;
    private Integer cTecBigType;
    private Integer cTecSmallType;
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
    private String cPath;
    private String cTag;
    private Integer count;
    /*看了多少个*/
    private Integer lookCount;
    private List<ChapterCourse> chapterCourseList;
    private Integer cPop;

    public Integer getcPop() {
        return cPop;
    }

    public void setcPop(Integer cPop) {
        this.cPop = cPop;
    }

    public List<ChapterCourse> getChapterCourseList() {
        return chapterCourseList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }

    public String getcPath() {
        return cPath;
    }

    public void setcPath(String cPath) {
        this.cPath = cPath;
    }

    public void setChapterCourseList(List<ChapterCourse> chapterCourseList) {
        this.chapterCourseList = chapterCourseList;
    }

    public void setcTecSmallType(Integer cTecSmallType) {
        this.cTecSmallType = cTecSmallType;
    }

    public String getcTag() {
        return cTag;
    }

    public void setcTag(String cTag) {
        this.cTag = cTag;
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

    public int getcTecBigType() {
        return cTecBigType;
    }

    public void setcTecBigType(Integer cTecBigType) {
        this.cTecBigType = cTecBigType;
    }

    public int getcTecSmallType() {
        return cTecSmallType;
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

    @Override
    public String toString() {
        return "Course{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cTecBigType=" + cTecBigType +
                ", cTecSmallType=" + cTecSmallType +
                ", cIntro='" + cIntro + '\'' +
                ", cAuthor='" + cAuthor + '\'' +
                ", cColumnIntro='" + cColumnIntro + '\'' +
                ", delflag='" + delflag + '\'' +
                ", cChapterCount=" + cChapterCount +
                ", cCollection=" + cCollection +
                ", cUploadTime=" + cUploadTime +
                ", cUploader='" + cUploader + '\'' +
                ", cPictureUrl='" + cPictureUrl + '\'' +
                ", cType='" + cType + '\'' +
                ", cPath='" + cPath + '\'' +
                ", cTag='" + cTag + '\'' +
                ", count=" + count +
                ", lookCount=" + lookCount +
                ", chapterCourseList=" + chapterCourseList +
                '}';
    }
}
