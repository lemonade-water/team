package com.sky.team.business.controller;

import com.sky.team.business.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseController {
    @Autowired
    private CourseController courseController;
    /*测试添加课程*/
    @Test
    public void addCourse(){
        Course course = new Course();
        course.setcId("1234");
        course.setcName("赵志敏");
        course.setcTecBigType(1);
        course.setcTecSmallType(1);
        course.setcIntro("牛b");
        course.setcAuthor("张三");
        course.setcColumnIntro("这是一个产品");
        course.setcChapterCount(12);
        course.setcCollection(200);
        course.setcUploadTime(null);
        course.setcUploader("李四");
        course.setcPictureUrl(null);
        course.setcType(null);
        course.setDelflag(null);
        Course course1 = courseController.addCourse(course);
        System.out.println(course1.toString());
    }

    /*测试查询所有课程*/
    @Test
    public void getCourse(){
        System.out.println(courseController.getCourse().toString());
    }

    /*测试修改课程*/
    @Test
    public void updCourse(){
        Course course = new Course();
        course.setcId("1234");
        course.setcName("赵志敏");
        course.setcTecBigType(2);
        course.setcTecSmallType(2);
        course.setcIntro("强大");
        course.setcAuthor("蒋浩");
        course.setcColumnIntro("这是一个产品");
        course.setcChapterCount(12);
        course.setcCollection(200);
        course.setcUploadTime(null);
        course.setcUploader("大圣");
        course.setcPictureUrl(null);
        course.setcType(null);
        course.setDelflag(null);
        //System.out.println(courseController.UpdCourse(course.getcId()).toString());
    }
}
