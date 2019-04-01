package com.sky.team.business.dao;

import org.springframework.stereotype.Repository;
import com.sky.team.business.pojo.PersonVideo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface PersonVideoDao {

    //int userUpload(MultipartFile file);
    /*审核视频*/
    boolean corrction(String personVideoId,String type);

}
