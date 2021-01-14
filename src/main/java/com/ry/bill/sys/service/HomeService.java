package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.vo.r_realVo;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-03 12:16
 * @description 主页服务接口
 */
public interface HomeService extends IService<r_realVo> {

    /*
   获取地图摔倒坐标点
    */
    List<Map<String,Object>> realJW();

    /*
    获取设备总数量、在线数量、离线数量
     */
    List<Map<String,Object>> realSUM();
}
