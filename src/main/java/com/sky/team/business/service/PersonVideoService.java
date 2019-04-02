package com.sky.team.business.service;

import com.sky.team.business.pojo.PersonVideo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonVideoService {

    /*用户上传*/
    boolean userUpload(MultipartFile file,PersonVideo personVideo);

    List<PersonVideo> getPersonVideoList(Integer num);
}
