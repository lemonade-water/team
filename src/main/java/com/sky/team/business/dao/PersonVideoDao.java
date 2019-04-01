package com.sky.team.business.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface PersonVideoDao {

    /*审核视频*/
    boolean corrction(String personVideoId,String type);
}
