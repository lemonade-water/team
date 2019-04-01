package com.sky.team.business.service;

import com.sky.team.business.pojo.PersonVideo;
import org.springframework.web.multipart.MultipartFile;

public interface PersonVideoService {

    /*用户上传*/
    public PersonVideo userUpload(MultipartFile file);

}
