package com.sky.team.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    /*得到所有课程分类*/
    @RequestMapping(value = "/api/getCourseType")
    public HashMap<CourseType,List<CourseType>> getCourseType(){

        return courseService.getCourseType();
    }

    /*查询所有的课程*/
    @RequestMapping("/api/getCourse")
    public PageHelper getCourse(PageHelper pageHelper){
        return courseService.getAllCourse(pageHelper);
    }

    /*插入新的课程*/
    @RequestMapping(value = "/api/addCourse" ,method = RequestMethod.POST)
    public Course addCourse(Course course){
        return courseService.addCourse(course);
    }

    /*根据id删除课程*/
    @RequestMapping(value = "/api/delCourse/{cId}" ,method = RequestMethod.GET)
    public String delCourse(@PathVariable("cId") String cId){
        int result = courseService.delCourse(cId);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    /*根据id修改课程*/
    @RequestMapping(value = "/api/updCourse" ,method = RequestMethod.POST)
    public String UpdCourse(@RequestBody Course course){
        int result = courseService.UpdCourse(course);
        if(result>=1){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }


}
