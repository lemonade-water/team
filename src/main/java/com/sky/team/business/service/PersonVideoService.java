package com.sky.team.business.service;

import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.util.PageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonVideoService {

    /*用户上传*/
    boolean userUpload(MultipartFile file,PersonVideo personVideo,String userid,String tag,String describe);

    /*视频热度变化*/
    int UpdPersonVideo(String userid);

    List<PersonVideo> getPersonVideoList(Integer num);

    /*审核视频*/
    boolean correction(String personvideoid,String type);

    PageHelper adminGetPersonVideo(PageHelper pageHelper);

    /*管理员删除微视频*/
    int delVideo(PersonVideo personVideo);

    /*个人上传记录*/
    int uploadRecord(PersonVideo personVideo,String userid);
}
