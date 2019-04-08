package com.sky.team.business.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.dao.CourseTypeDao;
import com.sky.team.business.dao.UserDao;
import com.sky.team.business.pojo.*;
import com.sky.team.business.pojo.Collection;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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


    @Value("${video-path-geek}")
    private String videoPathGeek;

    @Value("${video-static-pattern}")
    private String videoStaticPattern;

    /*阿星*/
    @Value("${IntelligenceIP}")
    private String IntelligenceIP;

    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public HashMap<String, List<CourseType>> getCourseType() {
        List<CourseType> list = courseTypeDao.getCourseType();
        if(list.size()==0){
            return null;
        }
        HashMap<String, List<CourseType>> hashMap = new HashMap<>();
        for(CourseType courseType:list){
            if(courseType.getGrade().equals("1")){
                List<CourseType> courseTypeList = new ArrayList<>();
                for(CourseType courseType1:list){
                    if(courseType1.getParentType()==(courseType.getcTecType())){
                        courseTypeList.add(courseType1);
                    }
                }
                hashMap.put(courseType.toString(),courseTypeList);
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
    public Course addCourse(Course course,String userid,MultipartFile multipartFile) {
        /*
        * 生成视频目录  通过时间
        * 创建文件夹
        * */
        Date date =new Date();
        String s = String.valueOf(date.getTime());
        String path = userid+ File.separator+s;

        course.setcId(UUID.randomUUID().toString().replace("-",""));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss");
        simpleDateFormat.format(date);
        course.setcUploadTime(date);
        /*session*/
        try{
            course.setcUploader("管理员");
            User user = userDao.getUser(userid);
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
        File file = new File(videoPath+videoPathGeek+path);

        if(file.exists()){
            file.mkdirs();
        }else{
            file.mkdirs();
        }
        course.setcPath(videoStaticPattern+videoPathGeek+path);

        /*插入封面*/
        File img = new File(videoPath+videoPathGeek+path+File.separator+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        course.setcPictureUrl(videoStaticPattern+videoPathGeek+path+File.separator+multipartFile.getOriginalFilename());
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
    public PageHelper getAllCourse(PageHelper pageHelper,String userid) {
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

        List<Course> courses = courseDao.queryCourse(pageHelper.getQuery(), pageHelper.getcTecBigType(), pageHelper.getcTecSmallType(), pageHelper.getPageIndex(), pageHelper.getLimit(),userid);

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
            String url = IntelligenceIP+"/sketch/close/?pid="+cid+"&num="+sketchNum+"&opt=0";
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

        /*查询Course*/
        Course course = courseDao.getCourseById(chapterCourse.getcId());
        /*得到目录*/
        String s = course.getcPath();
        // /video/极客时间/111\1554534925621
        String substring = s.substring(s.indexOf(videoStaticPattern) + videoStaticPattern.length());
        /*创建文件夹*/
        String chaptername = String.valueOf(new Date().getTime());
        String chapterPath = videoPath+substring+File.separator+chaptername;
        File file = new File(chapterPath);
        if(!file.exists()){
            file.mkdirs();
        }
        /*插入数据*/
        chapterCourse.setChPath(s+File.separator+chaptername);
        chapterCourse.setChapterId(chaptername);
        chapterCourse.setDelflag("0");

        courseDao.addChapter(chapterCourse);
        return true;
    }



    /*上传课程*/
    @Override
    public ResultMessage uploaderCourse(MultipartFile file, String chapterId, String subsectionName) {

        /*查询出章节所在的*/
        ChapterCourse chapterCourse = courseDao.findCourseById(chapterId);
        String s= chapterCourse.getChPath();
        String substring = s.substring(s.indexOf(videoStaticPattern) + videoStaticPattern.length());
        /*先建文件夹*/
        String filename = String.valueOf(new Date().getTime());
        String url = videoPath+videoPathGeek+substring+File.separator+filename+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File file1 = new File(url);
        /*存数据库*/
        Subsection subsection = new Subsection();
        subsection.setChapterId(chapterId);
        subsection.setSubsectionId(filename);
        subsection.setSubsectionName(subsectionName);
        subsection.setVideoFormat(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
        subsection.setVideoUrl(videoStaticPattern+url);
        BigDecimal size = new BigDecimal(file.getSize());
        BigDecimal mod = new BigDecimal(1024);
        float videoSize = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        subsection.setVideoSize(videoSize);
        courseDao.addSubsection(subsection);
        try {
            file.transferTo(file1);
            return ResultMessage.setResultMessage("200","成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultMessage.setResultMessage("400","失败");
        }

    }

    /*收藏*/
    @Override
    @Transactional
    public boolean collection(String cId,String userid) {
        try{
            courseDao.collection(cId,userid);
            courseDao.addCollection(cId);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<Collection> getCollection(String userid) {
        return courseDao.selectCollection(userid);
    }

    @Override
    public boolean delCollection(String cId, String username) {

        return courseDao.delCollection(cId,username);
    }

    @Override
    public boolean courseCollection(String cId, String username) {
        if(courseDao.courseCollection(username,cId)!=null){
            return true;
        }else
            return false;
    }

    /*添加历史纪录*/

    @Override
    @Transactional
    public void addUCourse(String userid, String cId, String chapterId, String subsectionId,Date date) {
        UCourse uCourse = courseDao.selectUCourse(userid, cId, chapterId, subsectionId);
        if(uCourse!=null){
            /*更新*/
            courseDao.updateCourse(userid,cId,chapterId,subsectionId,date,new Date());
        }else {
            courseDao.addUCourse(userid,cId,chapterId,subsectionId,date);
        }

    }

}
