package com.sky.team.business.controller;


import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    /*得到所有课程分类*/
    public HashMap<CourseType,List<CourseType>> getCourseType(){

        return courseService.getCourseType();
    }
}
