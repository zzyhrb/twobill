package com.ry.bill.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fpuser")
public class User implements Serializable {

    private  static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer	id;
    private String	uname;
    private String	upassword;
    private String department;
    private String rname;
    private String  rid;
    private Date ctime;
    @TableField(exist = false)
    private String  powname;
    @TableField(exist = false)
    private String roleName;

}
