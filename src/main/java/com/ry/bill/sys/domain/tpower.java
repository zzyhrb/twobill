package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author CKFuture
 * @since 2020-12-24 14:56
 * 机构表实体类
 */
@Data
@TableName("fpower")//对应具体数据表 否则对应的查询表名会出错
public class tpower {

    @TableId(value = "id",type = IdType.INPUT) //type = IdType.INPUT,没有的话无法插入主键
    //机构子id
    private String id;
    // 机构父id
    private String pid;
    //机构名称
    private String name;
    //机构类型
    private String type;
    //创建时间
    private Date ctime;

    /*
    创建时间 自动填充注解
     */
    @TableField(fill= FieldFill.INSERT)
    private Date gmt_create;

    /*
    修改时间 自动填充注解
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date gmt_modified;

    /*
    乐观锁
     */
    @Version
    private int version;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
