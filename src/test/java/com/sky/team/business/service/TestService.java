package com.sky.team.business.service;

import com.sky.team.business.pojo.CourseType;
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
}
