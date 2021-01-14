package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.mapper.froleMapper;
import com.ry.bill.sys.service.froleService;
import com.ry.bill.sys.vo.RolePowerMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-26 20:11
 * 角色服务接口实现类
 */
@Service
public class froleServiceImpl extends ServiceImpl<froleMapper, frole> implements froleService {

    @Autowired
    private froleMapper _froleMapper;

    //获取角色-机构-权限接口的实现方法
    @Override
    public List<RolePowerMenuVo> getList(RolePowerMenuVo _RolePowerMenuVo) {
        return _froleMapper.getList(_RolePowerMenuVo);
    }

    @Override
    public List<Map<String, Object>> list(int id) {
        return _froleMapper.list(id);
    }
}
