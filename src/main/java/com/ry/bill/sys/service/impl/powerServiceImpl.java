package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.mapper.powerMapper;
import com.ry.bill.sys.service.powerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class powerServiceImpl extends ServiceImpl<powerMapper, tpower> implements powerService {
    @Autowired
    private powerMapper _powerMapper;

    @Override
    public List<String> listObjs(tpower _tpower){
        return _powerMapper.listObjs(_tpower);
    }

    @Override
    public String getTreeId(String parentId) {
        String maxTreeId = _powerMapper.getMaxTreeId(parentId);
        if("".equals(maxTreeId) || null == maxTreeId){
            return parentId+"01";
        }else{
            maxTreeId = String.valueOf(Integer.parseInt(maxTreeId)+1);
            return maxTreeId.length() % 2 ==0?maxTreeId:"0"+maxTreeId;
        }
    }

    @Override
    public List<Map<String,Object>> getPowerList() {
        return _powerMapper.getPowerList();
    }

    @Override
    public List<Map<String, Object>> getagency() {
        return _powerMapper.getagency();
    }
}
