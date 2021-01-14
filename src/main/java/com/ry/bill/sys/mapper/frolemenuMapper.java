package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.domain.frolemenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-27 14:01
 * 角色与菜单关系表数据操作类
 */
@Repository
public interface frolemenuMapper extends BaseMapper<frolemenu> {

    @Select("SELECT id,rid,mid,mname FROM frolemenu")
    List<frolemenu> getlist(@Param("frolemenu") frolemenu _frolemenu);

    @Select("SELECT mname FROM frolemenu WHERE rid = #{id}")
    public List<Map<String, Object>> list(@Param("id") int id);
}
