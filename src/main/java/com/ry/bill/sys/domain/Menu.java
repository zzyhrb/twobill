package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fmenu")
public class Menu {

    private  static final long serialVersionUID=1L;

    private String  id;
    private String name;
    private String pid;
    private String uri;
    private String orders;
    private String icon;
    private Date ctime;
    private Integer level;


}
