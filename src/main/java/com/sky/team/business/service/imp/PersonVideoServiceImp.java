package com.sky.team.business.service.imp;

import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.dao.UserDao;
import com.sky.team.business.pojo.PersonVideo;
import com.sky.team.business.pojo.User;
import com.sky.team.business.service.PersonVideoService;
import com.sky.team.business.util.PageHelper;
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
import java.util.UUID;


@Service
public class PersonVideoServiceImp implements PersonVideoService {
    @Autowired
    private PersonVideoDao personVideoDao;

    @Value("${video-path}")
    private String videoPath;

    @Autowired
    private UserDao userDao;
    @Value("${video-path-yhsc}")
    private String videoPathYhsc;

    @Value("${video-static-pattern}")
    private String videoStaticPattern;
    @GetMapping(value = "file")
    public String file() {
        return "file";
    }


    @Override
    @Transactional
    public boolean userUpload(MultipartFile file,PersonVideo personVideo,String userid,String tag,String describe) {

        try {
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            if (file.isEmpty()) {
                System.out.println("文件为空");
            }

            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = videoPath + videoPathYhsc + userid+"/" + format.format(new Date()); // 上传后的路径


            BigDecimal size = new BigDecimal(file.getSize());
            BigDecimal mod = new BigDecimal(1024);
            //除两个1024，保留两位小数，进行四舍五入
            float videoSize = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

            /*设置数据库同步插入的值*/
            String url = videoPathYhsc + userid+"/" + format.format(new Date());

            personVideo.setPersonVideoId(UUID.randomUUID().toString());
            personVideo.setPersonVideoName(describe);
            personVideo.setPersonVideoUploader(userid);
            personVideo.setPersonVideoName(fileName);

            personVideo.setPersonVideoSize(videoSize);
            personVideo.setPersonVideoTime(new Date());
            User user = userDao.getUser(userid);
            if("1".equals(user.getRole().getRoleId())){
                personVideo.setPersonStatus(1);
            }else {
                personVideo.setPersonStatus(0);
            }

            personVideo.setPersonVideoPop(0);
            personVideo.setPersonVideoTag(tag);
            personVideo.setPersonVideoIntro(describe);

            File file2 = new File(filePath);
            if(!file2.exists()){
                file2.mkdirs();
            }

            //新文件名为登录ID+上传时间+文件后缀
            fileName = userid + format.format(new Date()) + suffixName; // 新文件名
            personVideo.setPersonVideoUrl(videoStaticPattern+url+"/"+fileName);
            File dest = new File(filePath +File.separator+fileName);
            file.transferTo(dest);
        }catch (Exception e){
            return false;
        }
        personVideoDao.userUpload(personVideo);
        return true;
    }


    @Override
    public List<PersonVideo> getPersonVideoList(Integer num) {
        return personVideoDao.getPersonVideoList(num);
    }

    /*审核视频*/
    @Override
    public boolean correction(String personvideoid, String type) {
        /*1通过*/
//        2不通过
        return personVideoDao.correction(personvideoid,type);
    }

    /*分页*/
    @Override
    @Transactional
    public PageHelper adminGetPersonVideo(PageHelper pageHelper) {
        /*查询所有的总数*/
        Integer count = personVideoDao.getPersonVideoCount();
        pageHelper.setTotalCount(count);
        pageHelper.setLimit(15);
        if(pageHelper.getPage()<=0){
            pageHelper.setPage(1);
        }
        if(pageHelper.getPage()>pageHelper.getTotalCount()){
            pageHelper.setPage(pageHelper.getTotalCount());
        }
        if(pageHelper.getPageIndex()<0){
            pageHelper.setPageIndex(0);
        }
        List<PersonVideo> personVideos = personVideoDao.queryPersonList(pageHelper.getPageIndex(), pageHelper.getLimit());
        pageHelper.setList(personVideos);
        return pageHelper;
    }

    @Override
    public int UpdPersonVideo(PersonVideo personVideo){
        return personVideoDao.UpdPersonVideo(personVideo);
    }

    @Override
    public int delVideo(PersonVideo personVideo){
        return personVideoDao.delVideo(personVideo);
    }

    @Override
    public int uploadRecord(PersonVideo personVideo,String userid){
        personVideo.setPersonVideoUploader(userid);
        return personVideoDao.uploadRecord(personVideo);
    }

}
