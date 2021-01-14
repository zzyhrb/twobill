package com.ry.bill.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.vo.reportVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ReportsService extends IService<reportVo> {


    List<Map<String,Object>> getSUM(reportVo _reportVo);

    List<Map<String,Object>> getfequi(reportVo _reportVo);


}
