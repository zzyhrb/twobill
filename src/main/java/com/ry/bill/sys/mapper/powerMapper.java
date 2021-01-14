package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.domain.tpower;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-24 15"17
 * @Description   数据表tpower操作接口
 */
@Repository
public interface powerMapper extends BaseMapper<tpower> {

    @Select("SELECT id,pid,name,type,ctime from fpower WHERE 1=1 AND name like '%#{tpower.name}%'")
    List<String> listObjs(@Param("tpower") tpower _tpower);

    //通过父级编码获取子级最大编码
    @Select("SELECT max(id) FROM fpower WHERE pid = #{parentId}")
    String getMaxTreeId(@Param("parentId") String parentId);

    @Select("SELECT id,pid,name title FROM fpower")
    List<Map<String,Object>> getPowerList();

    @Select("SELECT fpower.`name` FROM fpower WHERE fpower.`type` = 'agency'")
    List<Map<String,Object>> getagency();
}
