package com.sky.team.business.service;

import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.util.PageHelper;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    /*fdfd*/
    @Autowired
    private CourseService courseService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${IntelligenceIP}")
    private String IntelligenceIP;
    @Autowired
    private  UserService userService;

    @Autowired
    private CommentService commentService;
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
        courseService.addCourse(course,"1");

    }
    
    @Test
    public void aaa() throws UnsupportedEncodingException {
        String url = IntelligenceIP+"/sketch/sensitive?txt="+"妈**的 哈哈";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        String outtxt = jsonObject.getString("outtxt");
        System.out.println(new String(outtxt.getBytes(),"UTF-8"));
    }

    @Test
    public void testGetEmail(){
        String userid="111";
        String username = "111";
        String userpassword = "111";
        String userEmail = "1107229735@qq.com";

        userService.getEmail(userid,username,userpassword,userEmail);
    }

    @Test
    public void testComment(){
        Comment comment = new Comment();
        comment.setCommentText("你妈的，希特勒，去死吧，我是宇宙无敌超级大哥！！！请教出你的国企，智障，stupid，bitch");
        ResultMessage comment1 =commentService.comment(comment);
        System.out.println(comment1);
    }
}
