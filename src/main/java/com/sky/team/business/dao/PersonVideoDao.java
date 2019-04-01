package com.sky.team.business.dao;

import com.sky.team.business.pojo.PersonVideo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface PersonVideoDao {

    public int userUpload(PersonVideo personVideo);
    /*审核视频*/
    boolean corrction(String personVideoId,String type);

}
