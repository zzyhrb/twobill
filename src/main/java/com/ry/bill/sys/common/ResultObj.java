package com.ry.bill.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj  {
	
	public static final ResultObj  LOGIN_SUCCESS=new ResultObj(200, "登陆成功");
	public static final ResultObj  LOGIN_ERROR_PASS=new ResultObj(-0, "登陆失败,用户名或密码不正确");



	private Integer code;
	private String msg;



}
