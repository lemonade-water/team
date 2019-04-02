package com.sky.team.business.controller;


import com.sky.team.business.pojo.ChapterCourse;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
    public Course addCourse(Course course, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String userid = (String)body.get("username");
        return courseService.addCourse(course,userid);
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


    /*通过c_id获得章节和小节*/
    @RequestMapping(value = "/api/getChapter")
    public List<Course> getChapter(@RequestParam("cId")String cId){
        return courseService.getChapter(cId);
    }


    /*推荐接口*/
    @RequestMapping(value = "/api/sketch/close",method = RequestMethod.GET)
    public List<Course> getSketch(@RequestParam("cId")String cid){
        return courseService.getSketchClose(cid);
    }


    /*添加章节*/
    @RequestMapping(value = "/api/addChapter")
    public boolean addChapter(ChapterCourse chapterCourse){
        return courseService.addChapter(chapterCourse);
    }
}
