package com.sky.team.business.service;

import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;

import java.util.List;

public interface CommentService {

    ResultMessage comment(Comment comment);

    List<Comment> getComment(String cId);

    boolean delcomment(String commentid);
}
