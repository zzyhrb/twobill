package com.ry.bill.sys.controller;


import com.ry.bill.sys.common.DataGridView;

import com.ry.bill.sys.service.LoginfoService;
import com.ry.bill.sys.service.UserService;
import com.ry.bill.sys.service.froleService;
import com.ry.bill.sys.service.powerService;

import com.ry.bill.sys.vo.PowerVo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginfoService loginfoService;

    @Autowired
    private UserService userService;
    @Autowired
    private powerService _powerService;
    @Autowired
    private froleService _froleService;



    @RequestMapping("")
    public DataGridView loadpow_tree(PowerVo powerVo){
        return null ;

    }


}