package com.sky.team.business.service.imp;

import com.sky.team.business.dao.CommentDao;
import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public ResultMessage comment(Comment comment) {
        try{
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
