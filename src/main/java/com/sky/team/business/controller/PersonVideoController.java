package com.sky.team.business.controller;

import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.service.PersonVideoService;
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
import java.util.UUID;

/**
 * 文件上传
 */
@RestController
public class PersonVideoController {
    @Autowired
    private PersonVideoService personVideoService;

    /*@Value("${video-path}")
    private String videoPath;

    @Value("${video-path-yhsc}")
    private String videoPathYhsc;

    @GetMapping(value = "file")
    public String file() {
        return "file";
    }*/

    @RequestMapping(value = "/api/userUpload" ,method = RequestMethod.POST)
    public PersonVideo userUpload(MultipartFile file){
        return personVideoService.userUpload(file);
    }

    /*@RequestMapping(value = "/userUpload",method=RequestMethod.POST)
    public String userUpload(MultipartFile file, HttpServletRequest request) {


        Format format = new SimpleDateFormat("yyyyMMddHHmmss");
        //String chapter = request.getParameter("chapter");
        //String subsection = request.getParameter("subsection");
        //System.out.println(chapter+","+subsection);
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = videoPath + videoPathYhsc +"//"+format.format(new Date())+"//"; // 上传后的路径
        //获取当前登录用户ID
        String principal;
        //发生异常则给予默认员工ID：2430
        try{
            principal= (String)SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            principal="2430";
        }
        //新文件名为登录ID+上传时间+文件后缀
        fileName = principal + format.format(new Date()) +  suffixName; // 新文件名
        //fileName = UUID.randomUUID() + format.format(new Date()) +  suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        //上传路径中的文件夹不存在则创建文件夹
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        return "上传成功！";
    }*/
}



