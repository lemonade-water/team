package com.sky.team.business.dao;


import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {
    List<Course> getCourseList();

    /*查询所有的课程分类*/
    List<CourseType> getCourseType();
    /*查询所有的课程*/
    List<Course> getCourse();
    /*添加课程*/
    public int addCourse(Course course);
    /*根据课程号删除*/
    public int delCourse(String cId);
    /*修改课程*/
    public int UpdCourse(Course course);

    /*查询总数
    *
    * private Integer cTecBigType;  //大类
    private Integer cTecSmallType; 小类
    * */
    Integer getCount(String query,Integer cTecBigType,Integer cTecSmallType);

    List<Course> queryCourse(String query, Integer cTecBigType, Integer cTecSmallType, int pageIndex, int i);
}
