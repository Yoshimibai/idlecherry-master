package com.sydney.entity;

public class Notice {
    private Integer noticeid;

    private Integer noticetypeid;

    private Integer userid;

    private String noticetime;

    private String noticecontent;

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public Integer getNoticetypeid() {
        return noticetypeid;
    }

    public void setNoticetypeid(Integer noticetypeid) {
        this.noticetypeid = noticetypeid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNoticetime() {
        return noticetime;
    }

    public void setNoticetime(String noticetime) {
        this.noticetime = noticetime;
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent;
    }
}