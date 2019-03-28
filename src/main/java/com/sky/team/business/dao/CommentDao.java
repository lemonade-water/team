package com.sky.team.business.dao;

import com.sky.team.business.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao {

    void insertComment(Comment comment);


}
