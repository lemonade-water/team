package com.sky.team.business.dao;

import org.springframework.stereotype.Repository;
import com.sky.team.business.pojo.PersonVideo;

import java.util.List;

@Repository
public interface PersonVideoDao {

    //int userUpload(MultipartFile file);
    /*审核视频*/
    boolean corrction(String personVideoId,String type);

    /*视频热度变化*/
    int UpdPersonVideo(PersonVideo personVideo);
    /*插入用户上传视频信息*/
    int userUpload(PersonVideo personVideo);

    /*管理员删除微视频*/
    int delVideo(PersonVideo personVideo);

    /*个人上传记录*/
    int uploadRecord(PersonVideo personVideo);

    List<PersonVideo> getPersonVideoList(Integer num);

    boolean correction(String personvideoid, String type);
}
