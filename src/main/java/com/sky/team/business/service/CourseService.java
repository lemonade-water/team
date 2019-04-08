package com.sky.team.business.service;

import com.sky.team.business.pojo.*;
import com.sky.team.business.util.PageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CourseService {

    /*查询所有课程分类以及小分类*/
    HashMap<String,List<CourseType>> getCourseType();
    /*查询所有课程*/
    List<Course> getCourse();
    /*添加课程*/
    public Course addCourse(Course course,String userid,MultipartFile file);
    /*根据课程号删除*/
    public int delCourse(String cId);
    /*修改课程*/
    public int UpdCourse(Course course);

    PageHelper getAllCourse(PageHelper pageHelper,String userid);

    List<Course> getChapter(String cId);

    List<Course> getSketchClose(String cid);

    boolean addChapter(ChapterCourse chapterCourse);

    ResultMessage uploaderCourse(MultipartFile file, String chapterId, String subsectionName);

    boolean collection(String cId ,String userid);

    List<Collection> getCollection(String userid);

    boolean delCollection(String cId, String username);

    boolean courseCollection(String cId, String username);

    void addUCourse(String userid, String cId, String chapterId, String subsectionId, Date date);
}
