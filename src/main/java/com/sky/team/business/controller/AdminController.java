package com.sky.team.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.dao.UserDao;
import com.sky.team.business.pojo.CountDepartment;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.PersonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {


    @Autowired
    private PersonVideoService personVideoService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private PersonVideoDao personVideoDao;
    @Autowired
    private UserDao userDao;
    /*审核视频*/
    @RequestMapping(value = "/api/correction",method = RequestMethod.GET)
    public boolean correction(@RequestParam("personVideoId")String personVideoId,@RequestParam("type")String type){
        /*审核视频*/
       return personVideoService.correction(personVideoId,type);
    }


    /*查询前五的收藏数*/
//    List<Course> countCollection();
//
//    /*查询前五的播放量*/
//    List<Course> countVolume();
    /**/
    /*查询前五的收藏数*/
    @RequestMapping(value = "/api/countCollection",method = RequestMethod.GET)
    public List<Course> countCollection(){
        /*审核视频*/
        return courseDao.countCollection();
    }
    /*查询前五的播放量*/
    @RequestMapping(value = "/api/countVolume",method = RequestMethod.GET)
    public List<Course> countVolume(){
        /*审核视频*/
        return courseDao.countVolume();
    }

//    /*微视频热度前五*/
//    List<PersonVideo> countPersonVideo();

    @RequestMapping(value = "/api/countPersonVideo",method = RequestMethod.GET)
    public List<PersonVideo> countPersonVideo(){
        /*审核视频*/
        return personVideoDao.countPersonVideo();
    }

//    /*活跃度*/
//    List<User> countActivity();
//
//    /*每个部门人数*/
//    List<CountDepartment> countDepartment();
//
//    /*总人数*/
//    Integer countUser();

    @RequestMapping(value = "/api/countActivity",method = RequestMethod.GET)
    public List<User> countActivity(){
        /*审核视频*/
        return userDao.countActivity();
    }

    @RequestMapping(value = "/api/countDepartment",method = RequestMethod.GET)
    public List<CountDepartment> countDepartment(){
        /*审核视频*/
        return userDao.countDepartment();
    }

    @RequestMapping(value = "/api/countUser",method = RequestMethod.GET)
    public Integer countUser(){
        /*审核视频*/
        return userDao.countUser();
    }
}
