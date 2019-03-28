package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.List;

public class ChapterCourse implements Serializable {

    private String chapterId;
    private String cId;
    private String chName;
    private String chSubsectionAccout;
    private String delflag;
    private List<Subsection> subsectionList;

    public List<Subsection> getSubsectionList() {
        return subsectionList;
    }

    public void setSubsectionList(List<Subsection> subsectionList) {
        this.subsectionList = subsectionList;
    }

    public String getChSubsectionAccout() {
        return chSubsectionAccout;
    }

    public void setChSubsectionAccout(String chSubsectionAccout) {
        this.chSubsectionAccout = chSubsectionAccout;
    }

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

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }
}
