package com.sky.team.business.dao;

import com.sky.team.business.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    void insertComment(Comment comment);


    List<Comment> getComment(String cId);

    boolean delcomment(String commentid);
}
