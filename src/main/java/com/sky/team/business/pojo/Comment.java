package com.sky.team.business.pojo;

import java.io.Serializable;
import java.util.Date;
public class Comment implements Serializable {
    private String commentId;
    private String parentId;
    private String commentText;
    private String commentUserId;
    private String commentUserName;
    private Date commentDate;
    private String cId;

    public Comment() {
    }

    public Comment(String commentId, String parentId, String commentText, String commentUserId, String commentUserName, Date commentDate, String cId) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.commentText = commentText;
        this.commentUserId = commentUserId;
        this.commentUserName = commentUserName;
        this.commentDate = commentDate;
        this.cId = cId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
