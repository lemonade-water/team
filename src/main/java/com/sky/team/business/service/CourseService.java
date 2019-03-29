package com.sky.team.business.service;

import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.util.PageHelper;

import java.util.HashMap;
import java.util.List;

public interface CourseService {

    /*查询所有课程分类以及小分类*/
    HashMap<CourseType,List<CourseType>> getCourseType();
    /*查询所有课程*/
    List<Course> getCourse();
    /*添加课程*/
    public Course addCourse(Course course);
    /*根据课程号删除*/
    public int delCourse(String cId);
    /*修改课程*/
    public int UpdCourse(Course course);

    PageHelper getAllCourse(PageHelper pageHelper);

    List<Course> getChapter(String cId);

    List<Course> getSketchClose(String cid);
}
