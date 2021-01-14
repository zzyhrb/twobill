package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author CKFuture
 * @since 2020-12-26 19:22
 * 角色实体类
 */
@Data
@TableName("frole")
public class frole {
    //角色ID
    private int id;
    //角色名称
    private String name;
    //角色备注
    private String remark;
    //创建时间
    private Date ctime;
    //机构代码
    private String agencyid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getAgencyId() {
        return agencyid;
    }

    public void setAgencyId(String agencyId) {
        this.agencyid = agencyId;
    }
}
