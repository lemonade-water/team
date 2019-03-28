package com.sky.team.business.controller;


import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*评论*/
    @RequestMapping(value = "/api/comment",method = RequestMethod.POST)
    public ResultMessage comment(@RequestBody Comment comment){

        return commentService.comment(comment);
    }
}
