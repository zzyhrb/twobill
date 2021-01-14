package com.ry.bill.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.bill.sys.common.ResultObj;
import com.ry.bill.sys.domain.Loginfo;
import com.ry.bill.sys.service.LoginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:登录前端控制器
 * @author zzy
 * @date 2020/9/25 10:00
 */
@RestController
@RequestMapping("login")
public class LoginController {

@Autowired
LoginfoService loginfoService;

	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd) {
		QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uname",loginname);
		queryWrapper.eq("upassword",pwd);
		List<Loginfo> list = this.loginfoService.list(queryWrapper);
		if (list.size()>0){
			return ResultObj.LOGIN_SUCCESS;
		}else {
			return ResultObj.LOGIN_ERROR_PASS;
		}

	}
}

