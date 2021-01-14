package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.vo.r_realVo;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-29 09:55
 * @description 实时数据服务接口
 */
public interface CountsService  extends IService<r_realVo> {

    //获取实时数据服务接口
    List<r_realVo> getRealList(r_realVo r_realVo);

    /**
     * 通过设备编码（SN）获取持有人信息服务接口
     * @param sn 设备编码
     * @return 设备持有人 手机号、注册时间
     */
    List<Map<String,Object>> r_yhList(String sn);

    /**
     * 通过电话号码获取护理用户信息
     * @param telephone 电话号码
     * @return 用户信息集合
     */
    List<Map<String,Object>> getc_cuserList(String telephone);

    /**
     * 通过用户id获取用户详情
     * @param uid
     * @return 用户绑定信息集合
     */
    List<Map<String,Object>> getc_cuserDetail(String uid);

    /**
     * 通过用户id获取定制消息
     * @param uid
     * @return 定制消息集合
     */
    List<Map<String,Object>> getc_cuserMsg(String uid);

    /*
    通过设备查询对饮的用户下的围栏信息
     */
    List<Map<String,Object>> getWl(String sn);

    /*
    获取用户指定时间内运动轨迹
     */
    List<Map<String,Object>> getGJ(String sn,String start,String end);
}