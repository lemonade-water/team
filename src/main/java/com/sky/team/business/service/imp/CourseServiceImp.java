package com.sky.team.business.service.imp;

import com.sky.team.business.dao.CourseDao;
import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.CourseType;
import com.sky.team.business.service.CourseService;
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
    @Override
    @Transactional
    public HashMap<CourseType, List<CourseType>> getCourseType() {
        List<CourseType> list = courseDao.getCourseType();
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
}
