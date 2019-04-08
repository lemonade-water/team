package com.sky.team.business.dao;


import com.alibaba.fastjson.JSONArray;
import com.sky.team.business.pojo.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseDao {
   List<Course> getChapter(String cId);

    List<Course> getCourseList();


    /*查询所有的课程*/
    List<Course> getCourse();
    /*添加课程*/
    public int addCourse(Course course);
    /*根据课程号删除*/
    public int delCourse(String cId);
    /*修改课程*/
    public int UpdCourse(Course course);

    /*查询总数
    *
    * private Integer cTecBigType;  //大类
    private Integer cTecSmallType; 小类
    * */
     int getCourseCount(String query,Integer cTecBigType,Integer cTecSmallType);

    List<Course> queryCourse(String query, Integer cTecBigType, Integer cTecSmallType, int pageIndex, int i,String u_id);

    List<Course> getSketchClose(JSONArray list);

    List<Course> getSket(Integer limit);

    Course getCourseById(String s);

    void addChapter(ChapterCourse chapterCourse);

    ChapterCourse findCourseById(String chapterId);

    void addSubsection(Subsection subsection);

    void collection(String cId, String userid);

    List<Collection> selectCollection(String userid);

   boolean delCollection(String cId, String username);

   void addCollection(String cId);

    void addVolume(String cId);

   Collection courseCollection(String userid,String coursid);

   /*查询前五的收藏数*/
   List<Course> countCollection();

   /*查询前五的播放量*/
   List<Course> countVolume();


    void addUCourse(String userid, String cId, String chapterId, String subsectionId, Date date);

    UCourse selectUCourse(String userid, String cId, String chapterId, String subsectionId);

    /*更新*/
    void updateCourse(String userid, String cId, String chapterId, String subsectionId, Date date,Date updatetime);
}
