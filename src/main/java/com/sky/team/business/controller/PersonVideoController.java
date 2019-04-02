package com.sky.team.business.controller;

import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.service.PersonVideoService;
import com.sky.team.business.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 */
@CrossOrigin
@RestController
public class PersonVideoController {
    @Autowired
    private PersonVideoService personVideoService;


    @RequestMapping(value = "/api/userUpload" ,method = RequestMethod.POST)
    public boolean userUpload(@RequestParam("tag")String dec,@RequestParam("describe")String aa, MultipartFile file,HttpServletRequest request){
        System.out.println(dec);
        System.out.println(aa);
        PersonVideo personVideo = new PersonVideo();
        /*解析token*/
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String userid = (String)body.get("username");
        /*解析token*/
        return personVideoService.userUpload(file,personVideo,userid);
    }

    /*点击视频，热度变化*/
    @RequestMapping(value = "/api/updPersonVideo" ,method = RequestMethod.POST)
    public int UpdCourse(@RequestBody PersonVideo personVideo){
        return personVideoService.UpdPersonVideo(personVideo);
    }


    /*查询所有微课程*/
    @RequestMapping("/api/getPersonVideo")
    public List<PersonVideo> getPersonVideo(@RequestParam("num")Integer num){
        return personVideoService.getPersonVideoList(num);
    }
}



