package com.sky.team.business.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.sky.team.business.dao.CommentDao;
import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Value("${IntelligenceIP}")
    private String IntelligenceIP;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ResultMessage comment(Comment comment) {
        try{
            comment.setCommentId(String.valueOf(new Date().getTime()));
            String url = IntelligenceIP+"/sketch/sensitive/?txt="+comment.getCommentText();
            String forObject = restTemplate.getForObject(url, String.class);
            JSONObject jsonObject = JSONObject.parseObject(forObject);
            String outtxt = jsonObject.getString("outtxt");
            System.out.println(outtxt);
            comment.setCommentText(outtxt);
            comment.setCommentDate(new Date());
            commentDao.insertComment(comment);
            return ResultMessage.setResultMessage("200","评论成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.setResultMessage("400","评论失败");
        }

    }

    @Override
    public List<Comment> getComment(String cId) {
        return commentDao.getComment(cId);
    }

    @Override
    public boolean delcomment(String commentid) {
        return commentDao.delcomment(commentid);
    }
}
