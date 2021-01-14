package com.ry.bill.sys.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author CKFuture
 * @since 2020-12-27 10:12
 * 角色-机构-权限 视图类
 */
@Data

public class RolePowerMenuVo {
    //角色ID
    private int rid;
    //角色名称
    private String rname;
    //机构名称
    private String pname;
    //功能权限
    private String mname;
    //备注
    private String remark;
    //创建时间
    private Date ctime;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
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
}
