package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("fequi")
public class Equi {
    private Integer id;
    private String sn;
    private String snmode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date ctime;
    private Integer online;
    private String stype;
    private String agencyid;
    private String lwzq;
    private String sckg;
    @TableField(exist = false)
    private String powname;
}
