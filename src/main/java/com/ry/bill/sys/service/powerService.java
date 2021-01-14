package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.domain.tpower;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-24 19:39
 * @Description 机构服务接口
 *
 */
public interface powerService extends IService<tpower> {

    //获取机构列表json
    List<String> listObjs(tpower _tpower);

    //通过父级编码生成子级编码
    String getTreeId(String parentId);

    //机构列表
    List<Map<String,Object>> getPowerList();

    //获取机构名称  用于报表
    List<Map<String,Object>> getagency();
}
