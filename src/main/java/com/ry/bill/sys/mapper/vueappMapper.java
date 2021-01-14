package com.ry.bill.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.bill.sys.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-04 11:05
 * @description 手机APP接口 Mapper
 */
@Repository
public interface vueappMapper extends BaseMapper<UserVo> {

    /*
    用户登录 通过用户名以及密码登录
     */
    @Select("SELECT " +
            "id" +
            ",telephone" +
            ",apassword password " +
            " FROM fauser " +
            " WHERE telephone = #{telephone} AND apassword = #{password}")
    List<Map<String,Object>> login(@Param("telephone") String telephone,@Param("password") String password);

    /*
    微信登录
     */
    @Select("SELECT " +
            "id" +
            ",telephone" +
            ",apassword password " +
            " FROM fauser " +
            " WHERE unionid = #{unionid}")
    List<Map<String,Object>> wxlogin(@Param("unionid") String unionid);

    /*
    查询所有utype使用类型 (uid为0的以及用户自定义的)
     */
    @Select("SELECT " +
            "id" +
            ",tname text " +
            "FROM futype " +
            "WHERE uid IN (0,#{id})")
    List<Map<String,Object>> getutype(@Param("id") String id);

    /*
    自定义utype类型
     */
    @Insert("INSERT " +
            "INTO " +
            "futype(uid,tname) " +
            "VALUES(#{id},#{tname})")
    int setutype(@Param("id") String id,@Param("tname") String tname);
}
