package com.sydney.entity;

import java.math.BigDecimal;

public class UserCollection {
    private Integer collectionid;

    private Integer commid;

    private Integer userid;

    private String commname;

    private String commdesc;

    private BigDecimal price;

    private String collectiontime;

    public Integer getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(Integer collectionid) {
        this.collectionid = collectionid;
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

    public String getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "collectionid=" + collectionid +
                ", commid=" + commid +
                ", userid=" + userid +
                ", commname='" + commname + '\'' +
                ", commdesc='" + commdesc + '\'' +
                ", price=" + price +
                ", collectiontime='" + collectiontime + '\'' +
                '}';
    }
}