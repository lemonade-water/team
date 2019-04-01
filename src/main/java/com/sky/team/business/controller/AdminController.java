package com.sky.team.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.service.PersonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    @Autowired
    private PersonVideoService personVideoService;
    /*审核视频*/
    @RequestMapping("/correction")
    public void correction(@RequestBody String params){
        /*审核视频*/
        JSONObject jsonObject = JSONObject.parseObject(params);
        
    }
}
