package com.sky.team.business.controller;


import com.sky.team.business.pojo.Collection;
import com.sky.team.business.pojo.Comment;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.CommentService;
import com.sky.team.business.service.CourseService;
import com.sky.team.business.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CourseService courseService;
    /*评论*/
    @RequestMapping(value = "/api/comment",method = RequestMethod.POST)
    public ResultMessage comment(@RequestBody Comment comment, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        JwtUtil.validateToken(token);
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String username = (String)body.get("username");
        comment.setCommentUserId(username);
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

    @RequestMapping(value = "/api/collection")
    public ResultMessage Collection(@RequestParam String cId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        JwtUtil.validateToken(token);
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String username = (String)body.get("username");
        if(courseService.collection(cId,username)){
            return ResultMessage.setResultMessage("200","收藏成功");
        }else {
            return ResultMessage.setResultMessage("400","该课程已收藏");
        }
    }

    @RequestMapping(value = "/api/courseCollection")
    public boolean courseCollection(@RequestParam String cId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        JwtUtil.validateToken(token);
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String username = (String)body.get("username");
        /*ture被收藏*/
        /*false没被收藏*/
        return courseService.courseCollection(cId,username);
    }

    @RequestMapping(value = "/api/getCollection")
    public List<Collection> getCollection(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        JwtUtil.validateToken(token);
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String username = (String)body.get("username");
        return courseService.getCollection(username);
    }

    @RequestMapping(value = "/api/delCollection")
    public boolean delCollection(@RequestParam String cId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        JwtUtil.validateToken(token);
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ThisIsASecret")
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();
        String username = (String)body.get("username");
        return courseService.delCollection(cId,username);
    }

}
