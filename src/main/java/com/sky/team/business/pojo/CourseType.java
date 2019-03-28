package com.sky.team.business.pojo;

import java.io.Serializable;

public class CourseType implements Serializable {
    /*类型 id*/
    private Integer cTecType;
    /*名称*/
    private String cTecTypeName;
    /*大类还是小类*/
    private String  grade;
    /*属于哪个大类下的*/
    private Integer parentType;

    public Integer getcTecType() {
        return cTecType;
    }

    public void setcTecType(Integer cTecType) {
        this.cTecType = cTecType;
    }

    public String getcTecTypeName() {
        return cTecTypeName;
    }

    public void setcTecTypeName(String cTecTypeName) {
        this.cTecTypeName = cTecTypeName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }
}
