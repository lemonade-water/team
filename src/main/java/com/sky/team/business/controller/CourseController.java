package com.sky.team.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.pojo.ChapterCourse;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDao courseDao;

    /*得到所有课程分类*/
    @RequestMapping(value = "/api/getCourseType")
    public HashMap<String,List<CourseType>> getCourseType(){

        return courseService.getCourseType();
    }

    /*查询所有的课程*/
    @RequestMapping("/api/getCourse")
    public PageHelper getCourse(PageHelper pageHelper, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String userid = (String)body.get("username");
        return courseService.getAllCourse(pageHelper,userid);
    }

    /*插入新的课程*/
    @RequestMapping(value = "/api/addCourse" ,method = RequestMethod.POST)
    public Course addCourse(MultipartFile file,@RequestParam("cName")String cName
            ,@RequestParam("cTecBigType")Integer cTecBigType
            ,@RequestParam("cTecSmallType")Integer cTecSmallType
            ,@RequestParam("cIntro")String cIntro
            ,@RequestParam("cTag")String cTag
            ,@RequestParam("cColumnIntro")String cColumnIntro
                            ,HttpServletRequest request){
        /*file：图片*/
        Course course = new Course();
        course.setcName(cName);
        course.setcTecBigType(cTecBigType);
        course.setcTecSmallType(cTecSmallType);
        course.setcIntro(cIntro);
        course.setcTag(cTag);
        course.setcColumnIntro(cColumnIntro);
        System.out.println(file.getOriginalFilename());
        System.out.println(course);
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String userid = (String)body.get("username");
        return courseService.addCourse(course,userid,file);
        //return null;
    }

    /*根据id删除课程*/
    @RequestMapping(value = "/api/delCourse/{cId}" ,method = RequestMethod.GET)
    public ResultMessage delCourse(@PathVariable("cId") String cId){
        int result = courseService.delCourse(cId);
        if(result>=1){
            return ResultMessage.setResultMessage("200","删除成功");
        }else{
            return ResultMessage.setResultMessage("400","删除失败");
        }
    }

    /*根据id修改课程*/
    @RequestMapping(value = "/api/updCourse" ,method = RequestMethod.POST)
    public ResultMessage UpdCourse(@RequestBody Course course){
        int result = courseService.UpdCourse(course);
        if(result>=1){
            return ResultMessage.setResultMessage("200","修改成功");
        }else{
            return ResultMessage.setResultMessage("400","修改失败");
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

    /*管理员*/

    @RequestMapping(value = "/api/uploaderCourse",method = RequestMethod.POST)
    public ResultMessage uploaderCourse(MultipartFile file,@RequestParam("chapterId")String chapterId,@RequestParam("subsectionName")String subsectionName){
        return courseService.uploaderCourse(file,chapterId,subsectionName);
        //return null;
    }


    /*添加播放记录*/
    @RequestMapping(value = "/api/addRecord")
    public void addRecord(@RequestParam("cId")String cId,
                          @RequestParam("chapterId")String chapterId,
                          @RequestParam("subsectionId")String subsectionId,
                          @RequestParam(value = "userlearntime",required = false)Date date,
                          HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String userid = (String)body.get("username");
        courseService.addUCourse(userid,cId,chapterId,subsectionId,date);
    }

    /*播放量*/
    @RequestMapping(value = "/api/addVolume")
    public void addVolume(@RequestParam("cId")String cId,
                          HttpServletRequest request){
        courseDao.addVolume(cId);
    }
}
