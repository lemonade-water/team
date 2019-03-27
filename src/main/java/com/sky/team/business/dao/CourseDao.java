package com.sky.team.business.dao;


import com.sky.team.business.pojo.Course;
import com.sky.team.business.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {
    List<Course> getCourseList();

    User haah = new User();
}
