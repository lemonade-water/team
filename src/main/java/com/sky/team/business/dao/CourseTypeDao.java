package com.sky.team.business.dao;

import com.sky.team.business.pojo.CourseType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTypeDao {
    /*查询所有的课程分类*/
    List<CourseType> getCourseType();
}
