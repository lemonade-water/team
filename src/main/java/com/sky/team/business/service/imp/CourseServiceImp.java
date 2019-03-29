package com.sky.team.business.service.imp;

import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.dao.CourseTypeDao;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseTypeDao courseTypeDao;
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


    @Override
    public Course addCourse(Course course) {
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
        List<Course> courses = courseDao.queryCourse(pageHelper.getQuery(), pageHelper.getcTecBigType(), pageHelper.getcTecSmallType(), pageHelper.getPageIndex(), pageHelper.getLimit());

        pageHelper.setList(courses);
        return pageHelper;
    }

    @Override
    public List<Course> getChapter(String cId) {
        return courseDao.getChapter(cId);
    }
}
