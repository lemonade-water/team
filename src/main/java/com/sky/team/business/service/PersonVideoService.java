package com.sky.team.business.service;

import com.sky.team.business.pojo.ResultMessage;

public interface PersonVideoService {
    /*审核视频*/
    ResultMessage correction(String personvideoId, String type);
}
