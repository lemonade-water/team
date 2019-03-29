package com.sky.team.business.dao;


import com.sky.team.business.pojo.CourseType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseTypeDao courseTypeDao;


    @Test
    public void getCourseType(){
        List<CourseType> courseType = courseTypeDao.getCourseType();
        System.out.println(courseType.get(1).getParentType());
        System.out.println(courseType.get(1).getcTecType());
        System.out.println(courseType.get(1).getcTecTypeName());
        System.out.println(courseType.get(1).getGrade());
    }
}
