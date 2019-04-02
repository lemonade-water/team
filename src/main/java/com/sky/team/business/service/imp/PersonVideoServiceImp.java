package com.sky.team.business.service.imp;

import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.PersonVideoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.math.BigDecimal;
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
    @Transactional
    public boolean userUpload(MultipartFile file,PersonVideo personVideo,String userid) {

        try {
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
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


            BigDecimal size = new BigDecimal(file.getSize());
            BigDecimal mod = new BigDecimal(1024);
            //除两个1024，保留两位小数，进行四舍五入
            float videoSize = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

            /*设置数据库同步插入的值*/
            String url = videoPathYhsc + principal+"/" + format.format(new Date());
            Session session = SecurityUtils.getSubject().getSession(false);
            User user = (User)session.getAttribute("user");

            personVideo.setPersonVideoUploadernName(user.getUserName());
            personVideo.setPersonVideoName(fileName);
            personVideo.setPersonVideoUploader(principal);
            personVideo.setPersonVideoUrl(url);

            personVideo.setPersonStatus(0);
            personVideo.setPersonVideoPop(0);


            File file2 = new File(filePath);
            if(!file2.exists()){
                file2.mkdirs();
            }

            //新文件名为登录ID+上传时间+文件后缀
            fileName = principal + format.format(new Date()) + suffixName; // 新文件名
            File dest = new File(filePath + fileName);

        }catch (Exception e){
            return false;
        }
        return true;
    }


    @Override
    public List<PersonVideo> getPersonVideoList(Integer num) {
        return personVideoDao.getPersonVideoList(num);
    }

    @Override
    public int UpdPersonVideo(PersonVideo personVideo){
        return 0;
    }

}
