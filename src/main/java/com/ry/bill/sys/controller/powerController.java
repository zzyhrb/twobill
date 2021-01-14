package com.ry.bill.sys.controller;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.utils.ResponseCode;
import com.ry.bill.sys.utils.ResponseMsg;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-24 14:56
 * @Description 机构表控制器
 */
@Controller
@RequestMapping("/power")
public class powerController {

    // 机构服务接口
    private powerService _powerService;

    @Autowired
    public powerController(powerService ipowerService ) {
        this._powerService = ipowerService;
    }

    //页面初始化
    @RequestMapping("/page")
    public String Page(HttpServletRequest request){
        return "system/power/list";
    }

    //获取机构树
    @ResponseBody
    @RequestMapping("/tree")
    public Result tree(HttpServletRequest request){

        //SysOrgan organ = (SysOrgan) httpSession.getAttribute("organ");
        //SysUser user = (SysUser) httpSession.getAttribute("userInfo");
        //条件构造器
        QueryWrapper<tpower> queryWrapper = new QueryWrapper();

         queryWrapper.select("id","name","pid");
         queryWrapper.orderByAsc("id");
         List<Map<String, Object>> list = _powerService.listMaps(queryWrapper);

        JSONArray treeJson = TreeUtils.treeRecursionDataList(list, BigInteger.valueOf(Long.parseLong("0")),"id","name","pid", null);
        return Result.success(treeJson);
    }

    @ResponseBody
    @PostMapping("agencyTreeR")
    public Result agencyTreeR(HttpServletRequest request) {

        //getPowerList() 自己重新利用@SELECT sql方式 修改别名
        List<Map<String,Object>> list = _powerService.getPowerList();

        return Result.success(list);
    }


    //get 添加或编辑的页面
    @RequestMapping("/addOrEditPage")
    public ModelAndView addOrEditPage(HttpServletRequest request, String type,String parentId ,tpower _tpower){
        Map<String, Object> map = new HashMap<>();
        if("add".equals(type)){
            map.put("parentId",parentId);
        }else{
            _tpower = _powerService.getById(parentId);
            map.put("id",_tpower.getId());
            map.put("parentId",_tpower.getPid());
            //map.put("typeId",_tpower.getTypeId());
            map.put("tpower",_tpower);
        }
        map.put("type",type);
        return new ModelAndView("system/power/addOrEdit",map);
    }

    //post 添加或编辑页面
    @ResponseBody
    @RequestMapping("/addOrEdit")
    public Result addOrEdit(HttpSession httpSession,HttpServletRequest request,String id,String pid,String name, tpower _tpower, String type){
       // tpower user = (tpower)httpSession.getAttribute("userInfo");

        try {
            String powertype="";
            //判断机构类型
            if(pid.length()==2&&pid!="00")//company
            {
                powertype="company";
            }else if(pid.length()==4)
            {
                powertype="dept";
            }

            if ("add".equals(type)){
                String treeId = _powerService.getTreeId(pid); //生成机构代码
                _tpower.setId(treeId);
                _tpower.setPid(pid);
                _tpower.setCtime(new Date());
                _tpower.setName(name);
                _tpower.setType(powertype);
                _powerService.save(_tpower);
            } else {
                _powerService.saveOrUpdate(_tpower);
            }

            return Result.success(ResponseMsg.INSERT_UPDATE_SUCCESS);
        } catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999,ResponseMsg.INSERT_UPDATE_ERROR);
        }
    }

    //post
    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(HttpServletRequest request,String id){
        try {

            _powerService.removeById(id);
            return Result.success(ResponseMsg.DELETE_SUCCESS);
        } catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999,ResponseMsg.DELETE_ERROR);
        }
    }

}
