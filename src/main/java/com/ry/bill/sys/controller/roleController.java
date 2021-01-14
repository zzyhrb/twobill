package com.ry.bill.sys.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.bill.sys.domain.frole;
import com.ry.bill.sys.domain.frolemenu;
import com.ry.bill.sys.domain.tpower;
import com.ry.bill.sys.service.froleService;
import com.ry.bill.sys.service.frolemenuService;
import com.ry.bill.sys.utils.ResponseCode;
import com.ry.bill.sys.utils.ResponseMsg;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import com.ry.bill.sys.vo.RolePowerMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-26 20:17
 * 角色业务控制类
 */
@Controller
@RequestMapping("/role")
public class roleController {

    //角色服务接口
    private froleService _froleService;
    //角色与菜单关系表服务接口
    private frolemenuService _frolemenuService;

    @Autowired
    public roleController(froleService ifroleService) {
        this._froleService=ifroleService;
    }

    //页面初始化
    @RequestMapping("/page")
    public String Page(HttpServletRequest request){
        return "system/role/list";
    }

    //获取list
    @ResponseBody
    @PostMapping("/list")
    public Result list(HttpServletRequest request, String rname, RolePowerMenuVo _RolePowerMenuVo)
    {
        _RolePowerMenuVo.setRname(rname);//将查询参数装进类对象

        return Result.success(_froleService.getList(_RolePowerMenuVo));
    }

    //get 添加页面
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request){

        return new ModelAndView("system/role/add");
    }

    /***
     *
     * @param request
     * @param name 角色名称
     * @param menu 菜单ID集合
     * @param remark 角色备注
     * @param menuName 菜单名称集合
     * @param allidJG 机构代码
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRole")
    public Result addRole(HttpServletRequest request,String name, String menu, String remark, String menuName, String allidJG)
    {
        try {

          /*  String[] idlist = menu.split(",");
            String[] namelist = menuName.split(",");

            int maxid = 0;

            QueryWrapper<frole> queryWrapper =new QueryWrapper<frole>();
            queryWrapper.select("id");
            queryWrapper.orderByDesc("id").last("limit 1");
            frole _frole= _froleService.getOne(queryWrapper);
            int id=_frole.getId();
            if (String.valueOf(id).equals(""))
            {
                maxid = maxid + 1;
            }
            else
            {
                maxid = id + 1;
            }
            //添加 角色与菜单表数据
            for (int i = 0; i < idlist.length; i++)
            {
                frolemenu _frolemenu=new frolemenu();
                _frolemenu.setRid(maxid);
                _frolemenu.setMid(idlist[i]);
                _frolemenu.setMname(namelist[i]);
                _frolemenuService.save(_frolemenu);

            }

            //添加角色表（frole）
            _frole=new frole();
            _frole.setName(name);
            _frole.setRemark(remark);
            _frole.setCtime(new Date());
            _frole.setAgencyId(allidJG);
            _froleService.save(_frole);*/

            return Result.success(ResponseMsg.INSERT_UPDATE_SUCCESS);
        } catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999,ResponseMsg.INSERT_UPDATE_ERROR);
        }

    }


    /***
     *
     * @param request
     * @param rid 角色ID
     * @param _frole 角色对象
     * @return
     */
    @RequestMapping("/editRole")
    public ModelAndView editRole(HttpServletRequest request,String rid , frole _frole) {
        Map<String, Object> map = new HashMap<>();


        //mname 菜单名称集合
        List<Map<String, Object>> list = _froleService.list(Integer.parseInt(rid));
        map.put("agencyId", list.get(0).get("agencyId"));
        map.put("id", list.get(0).get("id"));//角色ID
        map.put("name", list.get(0).get("name"));//角色名称
        map.put("remark", list.get(0).get("remark"));//角色备注
        map.put("mname", list.get(0).get("mname"));//菜单名称集合
        map.put("mids", list.get(0).get("mids"));//菜单ID集合
        map.put("pname", list.get(0).get("pname"));//机构名称


        //[{"mname":"pc"},{"mname":"基础设置"},{"mname":"菜单管理"},{"mname":"机构管理"},{"mname":"角色管理"},{"mname":"人员管理"},{"mname":"设备管理"},{"mname":"监测统计"},{"mname":"实时数据"},{"mname":"护理用户"},{"mname":"统计报表"},{"mname":"销售设备"}]
        List<Map<String, Object>> list1 = _frolemenuService.list(Integer.parseInt(rid));

       String d= TreeUtils.Object2Json(list1).toString();

        map.put("treejson",d);


        return new ModelAndView("system/role/edit", map);
    }

}
