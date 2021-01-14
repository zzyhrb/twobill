package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.domain.frolemenu;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-27 14:01
 * 角色越菜单服务接口
 */
public interface frolemenuService  extends IService<frolemenu> {

    //List<frolemenu> getlist(frolemenu _frolemenu);

    List<Map<String,Object>> list(int id);
}
