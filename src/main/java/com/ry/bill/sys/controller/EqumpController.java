package com.ry.bill.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.bill.sys.common.DataGridView;
import com.ry.bill.sys.domain.Equi;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.service.EquiService;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.vo.EquiVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equi")
public class EqumpController {
    @Autowired
    private EquiService equiService;
    @Autowired
    private powerService _powerService;
    @RequestMapping("/loadAllEqui")
    public DataGridView loadAllEqum(EquiVo equiVo){
        Page<Equi> page = new Page<>(equiVo.getPage(), equiVo.getLimit());
        QueryWrapper<Equi> queryWrapper = new QueryWrapper<Equi>();
        queryWrapper.eq(StringUtils.isNotBlank(equiVo.getSn()),"sn",equiVo.getSn());
        this.equiService.page(page,queryWrapper);
        List<Equi> list= page.getRecords();
        for (Equi equi :list){
            String rid = equi.getAgencyid();
            tpower powname = _powerService.getById(rid);
            equi.setPowname(powname.getName());
        }
        return new DataGridView(page.getTotal(),list);
    }
}
