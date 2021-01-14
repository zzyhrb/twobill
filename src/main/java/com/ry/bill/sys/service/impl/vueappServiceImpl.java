package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.mapper.powerMapper;
import com.ry.bill.sys.mapper.vueappMapper;
import com.ry.bill.sys.service.vueappService;
import com.ry.bill.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-04 18:09
 * @description 手机APP服务接口实现类
 */
@Service
public class vueappServiceImpl extends ServiceImpl<vueappMapper, UserVo> implements vueappService {

    @Autowired
    private vueappMapper vueappMapper;


    //登录接口实现
    @Override
    public List<Map<String, Object>> login(String telephone, String password) { return vueappMapper.login(telephone,password); }
    //微信登录
    @Override
    public List<Map<String, Object>> wxlogin(String unionid) {
        return vueappMapper.wxlogin(unionid);
    }

    @Override
    public List<Map<String, Object>> getutype(String id) { return vueappMapper.wxlogin(id); }

    @Override
    public int setutype(String id, String tname) {
        return vueappMapper.setutype(id,tname);
    }
}
