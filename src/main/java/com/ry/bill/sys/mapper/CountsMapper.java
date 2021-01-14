package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.vo.r_realVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-29 09:30
 * @description 实时数据Mapper
 */
@Repository
public interface CountsMapper extends BaseMapper<r_realVo> {

    @Select("SELECT " +
            "a.sn" +
            ",a.fall" +
            ",a.inflation" +
            ",a.ctime" +
            ",a.jd" +
            ",a.wd" +
            ",a.bat" +
            ",a.qpzt" +
            ",a.cdzt" +
            ",(SELECT COUNT(1) FROM fbind WHERE sn = a.sn) con " +
            " FROM frealfall a " +
            " WHERE ctime = (SELECT MAX(ctime) FROM frealfall WHERE sn = a.sn)  AND a.sn LIKE '%${r_realVo.sn}%'" +
            " ORDER BY a.ctime DESC")
    List<r_realVo> getRealList(@Param("r_realVo") r_realVo r_realVo);

    /*
    通过设备编码（SN）获取用户信息
     */
    @Select("SELECT " +
            "fauser.telephone" +
            ",DATE_FORMAT(fauser.ctime,'%Y-%m-%d %H:%i:%s') ctime " +
            "FROM fauser INNER JOIN fbind ON fauser.id = fbind.uid " +
            "WHERE sn = ${sn} order by ctime desc")
    List<Map<String, Object>> r_yhList(@Param("sn") String sn);


    /*
    通过设备查询对应的用户下的围栏信息
     */
    @Select("SELECT " +
            "s.datajson" +
            ",s.title " +
            "FROM fenclosure s LEFT JOIN fbind b ON s.uid = b.uid " +
            "WHERE b.sn =${sn}  AND s.utype = (SELECT utype FROM fbind WHERE sn = ${sn})")
    List<Map<String,Object>> getWl(@Param("sn") String sn);

    /**
     * 获取用户指定时间内轨迹坐标点
     * @param sn 设备码
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    @Select("SELECT " +
            "jd" +
            ",wd " +
            "FROM frealfall " +
            "WHERE sn = '${sn}' AND ctime BETWEEN '${start}' AND '${end}' ORDER BY ctime ASC")
    List<Map<String,Object>> getGJ(@Param("sn") String sn,@Param("start") String start,@Param("end") String end);


    /**
     * 通过电话号码获取护理用户信息
     * @param telephone 电话号码
     * @return 用户信息集合
     */
    @Select("SELECT " +
            "fauser.id uid" +
            ",fauser.telephone" +
            ",DATE_FORMAT(fauser.ctime,'%Y-%m-%d %H:%i:%s') ctime" +
            ",(SELECT COUNT(1) FROM fbind WHERE uid = fauser.id) con " +
            " FROM fauser " +
            " WHERE telephone LIKE '%${telephone}%'"+
            " ORDER BY ctime DESC")
    List<Map<String, Object>> getc_cuserList(@Param("telephone") String telephone);

    /**
     * 护理用户详情
     * @param uid 护理用户Id
     * @return 信息集合
     */
    @Select("SELECT fbind.cusname" +
            ",fbind.allname" +
            ",fbind.sn" +
            ",DATE_FORMAT(fbind.ctime,'%Y-%m-%d %H:%i:%s') ctime" +
            ",futype.`tname` " +
            " FROM fbind INNER JOIN futype ON fbind.`utype` = futype.`id` " +
            " WHERE fbind.uid = ${uid}" +
            " ORDER BY ctime DESC")
    List<Map<String,Object>> getc_cuserDetail(@Param("uid") String uid);

    /**
     * 护理定制消息
     * @param uid 护理用户id
     * @return 消息集合
     */
    @Select("SELECT t.tname" +
            ",a.telephone" +
            ",a.faddress" +
            ",a.disease" +
            ",a.fposition fpodition " +
            "FROM fnews a INNER JOIN futype t ON a.utype = t.id " +
            "WHERE a.uid = ${uid}")
    List<Map<String,Object>> getc_cuserMsg(@Param("uid") String uid);
}
