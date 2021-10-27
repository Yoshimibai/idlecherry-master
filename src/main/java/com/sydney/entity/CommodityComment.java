package com.sydney.entity;

public class CommodityComment {
    private Integer commentid;

    private Integer commid;

    private Integer userid;

    private String content;

    private String commenttime;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    @Override
    public String toString() {
        return "CommodityComment{" +
                "commentid=" + commentid +
                ", commid=" + commid +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", commenttime='" + commenttime + '\'' +
                '}';
    }


}