package com.ry.bill.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author CKFuture
 * @since 2020-12-29 09:11
 * @description 实时数据视图类
 */
@Data
public class r_realVo {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    //设备编码
    private String sn;
    //穿戴状态
    private String cdzt;
    //气瓶状态
    private String qpzt;
    //跌倒状态
    private String fall;
    //保护状态
    private String inflation;
    //电量(%)
    private String bat;
    //详细地址
    private String address;
    //最新时间
    private String ctime;
    //用户数量
    private String con;
    //经度
    private double jd;
    //纬度
    private double wd;

    public double getJd() {
        return jd;
    }

    public void setJd(double jd) {
        this.jd = jd;
    }

    public double getWd() {
        return wd;
    }

    public void setWd(double wd) {
        this.wd = wd;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCdzt() {
        return cdzt;
    }

    public void setCdzt(String cdzt) {
        this.cdzt = cdzt;
    }

    public String getQpzt() {
        return qpzt;
    }

    public void setQpzt(String qpzt) {
        this.qpzt = qpzt;
    }

    public String getFall() {
        return fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public String getInflation() {
        return inflation;
    }

    public void setInflation(String inflation) {
        this.inflation = inflation;
    }

    public String getBat() {
        return bat;
    }

    public void setBat(String bat) {
        this.bat = bat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
}
