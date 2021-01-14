package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.domain.frolemenu;
import com.ry.bill.sys.mapper.froleMapper;
import com.ry.bill.sys.mapper.frolemenuMapper;
import com.ry.bill.sys.service.froleService;
import com.ry.bill.sys.service.frolemenuService;
import com.ry.bill.sys.vo.RolePowerMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class frolemenuImpl extends ServiceImpl<frolemenuMapper,frolemenu> implements frolemenuService {
    @Autowired
    private frolemenuMapper _frolemenuMapper;

   /* @Override
    public List<frolemenu> getlist(frolemenu _frolemenu) {
        return _frolemenuMapper.getlist(_frolemenu);
    }*/

    @Override
    public List<Map<String, Object>> list(int id) {
        return _frolemenuMapper.list(id);
    }
}


