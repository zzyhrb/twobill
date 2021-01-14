package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.mapper.CountsMapper;
import com.ry.bill.sys.mapper.HomeMapper;
import com.ry.bill.sys.service.HomeService;
import com.ry.bill.sys.vo.r_realVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, r_realVo> implements HomeService {

    @Autowired
    private HomeMapper homeMapper;


    @Override
    public List<Map<String, Object>> realJW() {
        return homeMapper.realJW();
    }

    @Override
    public List<Map<String, Object>> realSUM() {
        return homeMapper.realSUM();
    }
}
