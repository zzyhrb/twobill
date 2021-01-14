package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.mapper.ReportsMapper;
import com.ry.bill.sys.mapper.powerMapper;
import com.ry.bill.sys.service.ReportsService;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.vo.reportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportsServiceImpl extends ServiceImpl<ReportsMapper, reportVo> implements ReportsService {

    @Autowired
    private ReportsMapper _ReportsMapper;


    @Override
    public List<Map<String, Object>> getSUM(reportVo _reportVo) {
        return _ReportsMapper.getSUM(_reportVo);
    }

    @Override
    public List<Map<String, Object>> getfequi(reportVo _reportVo) {
        return _ReportsMapper.getfequi(_reportVo);
    }
}
