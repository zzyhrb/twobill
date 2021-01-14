package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.vo.RolePowerMenuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-26 19:56
 * 角色数据库操作接口
 */
@Repository
public interface froleMapper extends BaseMapper<frole> {

    //
    //查询角色表-机构表-功能表
    @Select("SELECT frole.id rid" +
            ", frole.name rname" +
            ",frole.`remark`" +
            ",GROUP_CONCAT(frolemenu.`mname` SEPARATOR ',') mname" +
            ",fpower.`name` pname " +
            "FROM frole LEFT JOIN frolemenu ON frole.id = frolemenu.rid LEFT JOIN fpower ON fpower.`id` = frole.`agencyId` " +
            "WHERE frole.`name` LIKE '${RolePowerMenuVo.rname}%' GROUP BY frole.id,fpower.`name`")
    List<RolePowerMenuVo> getList(@Param("RolePowerMenuVo") RolePowerMenuVo _RolePowerMenuVo);


    @Select("SELECT frole.`agencyId`" +
            ", frole.id" +
            ", frole.name" +
            ",frole.`remark`" +
            ",GROUP_CONCAT(frolemenu.`mname` SEPARATOR ',') mname" +
            ",GROUP_CONCAT(frolemenu.`mid` SEPARATOR ',') mids" +
            ",fpower.`name` pname " +
            "FROM frole LEFT JOIN frolemenu ON frole.id = frolemenu.rid LEFT JOIN fpower ON fpower.`id` = frole.`agencyId` " +
            "WHERE frole.`id` = #{id}" +
            "GROUP BY frole.id,fpower.`name`")
    List<Map<String,Object>> list(@Param("id") int id);


}
