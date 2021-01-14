package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-04 11:11
 * @description 手机APP接口 服务
 */
public interface vueappService extends IService<UserVo> {

    /*
    用户登录
     */
    List<Map<String,Object>> login(String telephone,String password);

    /*
    微信登录
     */
    List<Map<String,Object>> wxlogin(String unionid);

    /*
    查询所有utype使用类型 (uid为0的以及用户自定义的)
     */
    List<Map<String,Object>> getutype(String id);

    /*
    自定义utype类型
     */
    int setutype(String id,String tname);
}
