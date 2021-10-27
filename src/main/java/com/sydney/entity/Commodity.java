package com.sydney.entity;

import java.math.BigDecimal;

public class Commodity {
    private Integer commid;

    private String commname;

    private String commdesc;

    private BigDecimal price;

    private String image;

    private String updatetime;

    private Integer commstatus;

    private Integer categoryid;

    private Integer userid;

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public String getCommname() {
        return commname;
    }

    public void setCommname(String commname) {
        this.commname = commname;
    }

    public String getCommdesc() {
        return commdesc;
    }

    public void setCommdesc(String commdesc) {
        this.commdesc = commdesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCommstatus() {
        return commstatus;
    }

    public void setCommstatus(Integer commstatus) {
        this.commstatus = commstatus;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commid=" + commid +
                ", commname='" + commname + '\'' +
                ", commdesc='" + commdesc + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", commstatus=" + commstatus +
                ", categoryid=" + categoryid +
                ", userid=" + userid +
                '}';
    }
}