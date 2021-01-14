package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.vo.r_realVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-03 12：09
 * @description 主页Mapper
 */
@Repository
public interface HomeMapper extends BaseMapper<r_realVo> {

    /*
    获取地图摔倒坐标点
     */
    @Select("SELECT " +
            "t2.jd" +
            ",t2.wd" +
            ",t2.sn" +
            ",t2.fall " +
            " FROM frealfall t2 " +
            " WHERE t2.id = (SELECT t3.id FROM frealfall t3 WHERE t3.sn = t2.sn ORDER BY t3.ctime DESC LIMIT 1)")
    List<Map<String,Object>> realJW();

    /*
    获取设备总数量、在线数量、离线数量
     */
    @Select("SELECT " +
            "SUM(CASE e.online WHEN 1 THEN 1 ELSE 0 END) online" +
            ", SUM(CASE e.online WHEN 0 THEN 1 ELSE 0 END) noline" +
            ", COUNT(1) con " +
            "FROM fequi e")
    List<Map<String,Object>> realSUM();
}
