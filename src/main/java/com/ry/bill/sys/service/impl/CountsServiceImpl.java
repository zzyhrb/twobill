package com.ry.bill.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.bill.sys.mapper.CountsMapper;
import com.ry.bill.sys.service.CountsService;
import com.ry.bill.sys.vo.r_realVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-29 10:00
 * @description 实时数据服务接口实现类
 */
@Service
public class CountsServiceImpl extends ServiceImpl<CountsMapper, r_realVo> implements CountsService {
    @Autowired
    private CountsMapper _CountsMapper;

    @Override
    public List<r_realVo> getRealList(r_realVo _r_realVo) {
        return _CountsMapper.getRealList(_r_realVo);
    }

    @Override
    public List<Map<String, Object>> r_yhList(String sn) {
        return _CountsMapper.r_yhList(sn);
    }

    @Override
    public List<Map<String, Object>> getc_cuserList(String telephone) {
        return _CountsMapper.getc_cuserList(telephone);
    }

    @Override
    public List<Map<String, Object>> getc_cuserDetail(String uid) {
        return _CountsMapper.getc_cuserDetail(uid);
    }

    @Override
    public List<Map<String, Object>> getc_cuserMsg(String uid) {
        return _CountsMapper.getc_cuserMsg(uid);
    }

    @Override
    public List<Map<String, Object>> getWl(String sn) {
        return _CountsMapper.getWl(sn);
    }

    @Override
    public List<Map<String, Object>> getGJ(String sn, String start, String end) {
        return _CountsMapper.getGJ(sn,start,end);
    }
}
