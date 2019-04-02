package com.sky.team.business.service.imp;

import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.service.PersonVideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonVideoServiceImp implements PersonVideoService {
    @Autowired
    private PersonVideoDao personVideoDao;

    @Value("${video-path}")
    private String videoPath;

    @Value("${video-path-yhsc}")
    private String videoPathYhsc;

    @GetMapping(value = "file")
    public String file() {
        return "file";
    }


    @Override
    public boolean userUpload(MultipartFile file) {
        try {
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            //String chapter = request.getParameter("chapter");
            //String subsection = request.getParameter("subsection");
            //System.out.println(chapter+","+subsection);
            if (file.isEmpty()) {
                System.out.println("文件为空");
            }
            String principal;
            try {
                principal = (String) SecurityUtils.getSubject().getPrincipal();
                if(principal==null){
                    principal = "2430";
                }
            } catch (Exception e) {
                principal = "2430";
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = videoPath + videoPathYhsc + principal+"/" + format.format(new Date()); // 上传后的路径
            File file1 = new File(filePath);
            if(!file1.exists()){
                file1.mkdirs();
            }

            //发生异常则给予默认员工ID：2430

            //新文件名为登录ID+上传时间+文件后缀
            fileName = principal + format.format(new Date()) + suffixName; // 新文件名
            //fileName = UUID.randomUUID() + format.format(new Date()) +  suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            //上传路径中的文件夹不存在则创建文件夹
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<PersonVideo> getPersonVideoList(Integer num) {
        return personVideoDao.getPersonVideoList(num);
    }

}
