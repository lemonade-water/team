package com.sky.team.business.controller;


import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*评论*/
    @RequestMapping(value = "/api/comment",method = RequestMethod.POST)
    public ResultMessage comment(@RequestBody Comment comment){

        return commentService.comment(comment);
    }

    /*查询评论*/
    @RequestMapping(value = "/api/comment",method = RequestMethod.GET)
    public List<Comment> getComment(@RequestParam("cId") String cId){

        return commentService.getComment(cId);
    }

    /*删除评论*/
    @RequestMapping(value = "/api/delcomment",method = RequestMethod.GET)
    public boolean delcomment(@RequestParam("commentid") String commentid){

        return commentService.delcomment(commentid);
    }
}
