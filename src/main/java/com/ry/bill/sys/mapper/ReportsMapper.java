package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.vo.reportVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-30 14:59
 * @decription 报表Mapper
 */
@Repository
public interface ReportsMapper extends BaseMapper<reportVo> {

    @Select("SELECT p.`name` pname" +
            ",e.stype" +
            ",e.snmode" +
            ", SUM(CASE e.online WHEN 1 THEN 1 ELSE 0 END) online" +
            ", SUM(CASE e.online WHEN 0 THEN 1 ELSE 0 END) nonline" +
            ", COUNT(1) con " +
            "FROM fequi e RIGHT JOIN fpower p ON p.`id` = e.`agencyId` " +
            " WHERE p.type = 'company' AND 1=1 " +
            " AND e.ctime BETWEEN '${reportVo.start}' AND '${reportVo.end}'  " +
            " GROUP BY pname,stype,snmode")
    List<Map<String, Object>> getSUM(@Param("reportVo") reportVo _reportVo);

    @Select("SELECT " +
            "p.`name` pname " +
            " FROM fequi e RIGHT JOIN fpower p ON p.`id` = e.`agencyId` " +
            " WHERE p.type = 'company' " +
            " AND e.ctime BETWEEN '${reportVo.start}' AND  '${reportVo.end}' GROUP BY pname")
    List<Map<String,Object>> getfequi(@Param("reportVo") reportVo _reportVo);
}
