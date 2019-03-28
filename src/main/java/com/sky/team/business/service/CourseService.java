package com.sky.team.business.service;

import com.sky.team.business.pojo.CourseType;

import java.util.HashMap;
import java.util.List;

public interface CourseService {

    /*查询所有课程分类以及小分类*/
    HashMap<CourseType,List<CourseType>> getCourseType();
}
