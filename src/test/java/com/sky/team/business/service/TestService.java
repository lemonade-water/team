package com.sky.team.business.service;

import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.util.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    /*fdfd*/
    @Autowired
    private CourseService courseService;


    @Test
    public void getCourseType(){
        HashMap<CourseType, List<CourseType>> courseType = courseService.getCourseType();
        System.out.println(courseType);
    }


    @Test
    public void getCourse(){
        PageHelper pageHelper = new PageHelper();
        pageHelper.setPage(1);
        pageHelper.setQuery("");
        PageHelper allCourse = courseService.getAllCourse(pageHelper);
        System.out.println(allCourse);
    }


    /*课程推荐*/
    @Test
    public void setch(){
        courseService.getSketchClose("2");
    }

    /*测试添加课程*/
    @Test
    public void addCourse(){
        Course course = new Course();
        course.setcName("课程名字");
        course.setcTecBigType(1);
        course.setcTecSmallType(8);
        course.setcAuthor("课程作者");
        course.setcIntro("课程简介");
        courseService.addCourse(course);

    }
}
