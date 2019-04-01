package com.sky.team.business.service;

import com.sky.team.business.pojo.PersonVideo;
import org.springframework.web.multipart.MultipartFile;

public interface PersonVideoService {

    /*添加课程*/
    public PersonVideo userUpload(MultipartFile file);

}
