package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.vo.RolePowerMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-26 20：05
 * 角色服务接口
 */
public interface froleService extends IService<frole> {

    //获取角色-机构-功能权限列表
    List<RolePowerMenuVo> getList(RolePowerMenuVo _RolePowerMenuVo);

    List<Map<String,Object>> list(int id);
}
