package com.sky.team.business.service.imp;

import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.PersonVideoService;
import it.sauronsoftware.jave.Encoder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
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
    public boolean userUpload(MultipartFile file,PersonVideo personVideo) {

        try {
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            //String chapter = request.getParameter("chapter");
            //String subsection = request.getParameter("subsection");
            //System.out.println(chapter+","+subsection);
            if (file.isEmpty()) {
                System.out.println("文件为空");
            }
            //发生异常则给予默认员工ID：2430
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

            //计算文件时长大小
            long length = 0l;
            Encoder encoder = new Encoder();
            // 创建一个临时文件
            File file1 = new File("");
            // MultipartFile to File
            file.transferTo(file1);
            try {
                //getInfo()的参数是一个File,不能用MultipartFile
                //getDuration()获取得到的文件时长是一个以毫秒为单位的long类型的数值

                length = encoder.getInfo(file1).getDuration();
            } catch (Exception e) {

            }
            //打印一下文件时长
            System.out.println(length/1000/60+"分"+length/1000%60+"秒");

            //获取文件大小
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
            personVideo.setPersonVideoSize(videoSize);
            personVideo.setPersonStatus(0);
            //获取当前登录用户ID
            System.out.println(filePath);
            File file2 = new File(filePath);
            if(!file2.exists()){
                file2.mkdirs();
            }



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
            //程序结束时，删除临时文件
            deleteFile(file1);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    /*删除文件*/
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public List<PersonVideo> getPersonVideoList(Integer num) {
        return personVideoDao.getPersonVideoList(num);
    }

}
