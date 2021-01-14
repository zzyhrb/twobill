package com.ry.bill.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.bill.sys.common.DataGridView;
import com.ry.bill.sys.domain.Loginfo;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.service.LoginfoService;
import com.ry.bill.sys.service.UserService;
import com.ry.bill.sys.service.froleService;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.vo.LoginfoVo;
import com.ry.bill.sys.vo.PowerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */

@Api(description = "用户接口")
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


    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo,
                                       @RequestParam(value = "pageNo",defaultValue = "1",required = false)Integer pageNo,
                                       @RequestParam(value ="pageSize",defaultValue = "10",required = false)Integer pageSize) {
        IPage<Loginfo> page = new Page<>(pageNo,pageSize);
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
//        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.loginfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @ResponseBody
    @ApiOperation(value = "新增用户", notes = "新增注册")
    @RequestMapping(value = "/createUser/{loginname}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DataGridView queryUser(@RequestParam(value = "pageNo",defaultValue = "1",required = false)Integer pageNo,
                                  @PathVariable("loginname") String loginname,
                                  @RequestParam(value ="pageSize",defaultValue = "10",required = false)Integer pageSize) {
        IPage<Loginfo> page = new Page<>(pageNo,pageSize);
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(loginname),"loginname",loginname);
        queryWrapper.orderByDesc("logintime");
        this.loginfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }


    @ResponseBody
    @GetMapping("getUser/{id}")
    @ApiOperation(value="根据ID获取用户信息")
    public Loginfo getUser(@PathVariable("id") Long id){

        return loginfoService.getById(id);
    }

    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(LoginfoVo loginfoVo){
        Page<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper  = new QueryWrapper<Loginfo>().eq(StringUtils.isNotBlank(loginfoVo.getRname()),"uname",loginfoVo.getRname());
        this.userService.page(page,queryWrapper);
        List<Loginfo> list= page.getRecords();
        for (Loginfo loginfo :list){
            String rid = loginfo.getDepartment();
            tpower powname = _powerService.getById(rid);
            loginfo.setPowname(powname.getName());
            String rid1 = loginfo.getRid();
            frole rolname = _froleService.getById(rid1);
            loginfo.setRoleName(rolname.getName());
        }
        return new DataGridView(page.getTotal(),list);
    }

    @RequestMapping("")
    public DataGridView loadpow_tree(PowerVo powerVo){
        return null ;

    }


}