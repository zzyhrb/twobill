package com.ry.bill.sys.vo;

import com.ry.bill.sys.domain.Equi;
import lombok.Data;

@Data
public class EquiVo extends Equi {

    private Integer page;
    private Integer limit;

}
