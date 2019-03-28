package com.sky.team.business.service;

import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;

public interface CommentService {

    ResultMessage comment(Comment comment);
}
