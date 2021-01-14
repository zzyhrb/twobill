package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CKFuture
 * @since 2020-12-27 13:56
 * 角色和菜单关系实体类
 */
@Data
@TableName("frolemenu")
public class frolemenu {

    @TableId(value="id",type= IdType.AUTO)//自增主键
    private int id;
    //角色id
    private  int rid;
    //菜单id
    private String mid;
    //菜单名称
    private String mname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
