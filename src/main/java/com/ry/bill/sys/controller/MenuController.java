package com.ry.bill.sys.controller;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.bill.sys.domain.Menu;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.service.MenuService;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //获菜单构树
    @ResponseBody
    @RequestMapping("/tree")
    public Result tree(HttpServletRequest request){
        //条件构造器
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.select("id","name","pid");
        queryWrapper.orderByAsc("id");
        List<Map<String, Object>> list = menuService.listMaps(queryWrapper);
        System.out.println("menu ===="+list);
        JSONArray treeJson = TreeUtils.treeRecursionDataList(list, BigInteger.valueOf(Long.parseLong("0")),"id","name","pid", null);
        return Result.success(treeJson);
    }



}
