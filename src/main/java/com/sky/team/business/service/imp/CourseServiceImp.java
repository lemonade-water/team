package com.sky.team.business.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.dao.CourseTypeDao;
import com.sky.team.business.pojo.ChapterCourse;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseTypeDao courseTypeDao;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sketchNum}")
    private Integer sketchNum;

    @Value("${video-path}")
    private String videoPath;

    @Value("${video-path-yhsc}")
    private String videoPathYhsc;

    @Value("${video-static-pattern}")
    private String videoStaticPattern;
    @Override
    @Transactional
    public HashMap<CourseType, List<CourseType>> getCourseType() {
        List<CourseType> list = courseTypeDao.getCourseType();
        if(list.size()==0){
            return null;
        }
        HashMap<CourseType, List<CourseType>> hashMap = new HashMap<>();
        for(CourseType courseType:list){
            if(courseType.getGrade().equals("1")){
                List<CourseType> courseTypeList = new ArrayList<>();
                for(CourseType courseType1:list){
                    if(courseType1.getParentType()==(courseType.getcTecType())){
                        courseTypeList.add(courseType1);
                    }
                }
                hashMap.put(courseType,courseTypeList);
            }
        }
        return hashMap;
    }

    @Override
    public List<Course> getCourse() {
        return courseDao.getCourse();
    }



    /*管理员*/
    @Override
    @Transactional
    public Course addCourse(Course course) {
        /*
        * 生成视频目录  通过时间
        * 创建文件夹
        * */
        String principal;
        try{
            principal= (String)SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
          principal="2430";
        }
        Date date =new Date();
        String s = String.valueOf(date.getTime());
        String path = principal+ File.separator+s;

        course.setcId(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss");
        simpleDateFormat.format(date);
        course.setcUploadTime(date);
        /*session*/
        try{
            Session session = SecurityUtils.getSubject().getSession(false);
            User user = (User)session.getAttribute("user");
            course.setcUploader(user.getUserName());
            if(user.getRole().getRoleId().equals("1")||user.getRole().getRoleName().equals("管理员")){
                course.setcType("1");
            }else {
                course.setcType("0");
            }
        }catch (Exception e){
            course.setcUploader("管理员");
            course.setcType("0");
        }


        /*建文件夹*/
        //path = principal+ File.separator+s
        File file = new File(videoPath+videoPathYhsc+path);

        if(file.exists()){
            file.mkdirs();
        }else{
            file.mkdirs();
        }
        course.setcPath(videoStaticPattern+videoPathYhsc+path);
        courseDao.addCourse(course);
        return course;
    }

    @Override
    public int delCourse(String cId) {
        return courseDao.delCourse(cId);
    }

    @Override
    public int UpdCourse(Course course) {
        return courseDao.UpdCourse(course);
    }



    @Override
    @Transactional
    public PageHelper getAllCourse(PageHelper pageHelper) {
        Integer count = courseDao.getCourseCount(pageHelper.getQuery(), pageHelper.getcTecBigType(), pageHelper.getcTecSmallType());
        /*先查询总数*/
        pageHelper.setTotalCount(count);
        pageHelper.setLimit(8);
        if(pageHelper.getPage()<=0){
            pageHelper.setPage(1);
        }
        if(pageHelper.getPage()>pageHelper.getTotalCount()){
            pageHelper.setPage(pageHelper.getTotalCount());
        }
        if(pageHelper.getPageIndex()<0){
            pageHelper.setPageIndex(0);
        }
        String  principal;
        try{
            principal= (String)SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            principal="2430";
        }

        List<Course> courses = courseDao.queryCourse(pageHelper.getQuery(), pageHelper.getcTecBigType(), pageHelper.getcTecSmallType(), pageHelper.getPageIndex(), pageHelper.getLimit(),principal);

        pageHelper.setList(courses);
        return pageHelper;
    }

    @Override
    public List<Course> getChapter(String cId) {
        return courseDao.getChapter(cId);
    }

    @Override
    public List<Course> getSketchClose(String cid) {
        try{
            String url = "http://192.168.43.163:5000/sketch/close?pid="+cid+"&num="+sketchNum;
            String s = restTemplate.getForObject(url, String.class);
            JSONObject jsonObject = JSONObject.parseObject(s);
            JSONArray flag = jsonObject.getJSONArray("flag");

            return courseDao.getSketchClose(flag);

        }catch (Exception e){
            e.printStackTrace();
            /**/
            return courseDao.getSket(sketchNum);

        }

    }

    @Override
    public boolean addChapter(ChapterCourse chapterCourse) {
        /*添加课程章节*/

        return false;
    }
}
