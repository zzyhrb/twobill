package com.ry.bill.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ry.bill.sys.service.CountsService;
import com.ry.bill.sys.utils.GetLocation;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import com.ry.bill.sys.vo.WL;
import com.ry.bill.sys.vo.r_realVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CKFuture
 * @since 2010-12-29 08:57
 * @Description 实时数据控制器
 */
@Controller
@RequestMapping("/Counts")
public class CountsController {

    private CountsService _CountsService;

    @Autowired
    public CountsController(CountsService iCountsService) {
        this._CountsService = iCountsService;
    }


    @RequestMapping("/page")
    public String Page(HttpServletRequest request) {
        return "system/Counts/r_realList";
    }

    //获取实时列表
    @ResponseBody
    @RequestMapping("/r_realList")
    public Result r_realList(HttpServletRequest request, String sn, r_realVo _realVo) {
        _realVo.setSn(sn);//查询参数装入对象

        List<r_realVo> list = _CountsService.getRealList(_realVo);

        //利用经纬度 动态获取地址
        for (int i = 0; i < list.size(); i++) {
            r_realVo obj = list.get(i);
            String strAddress = GetLocation.getAdd(String.valueOf(obj.getWd()), String.valueOf(obj.getWd()));
            obj.setAddress(strAddress);
            list.set(i, obj);

        }
        return Result.success(list);
    }







    //=================================================用户信息==========================================================

    /**
     * 用户页面加载并传递SN
     *
     * @param request
     * @param sn
     * @return
     */
    @RequestMapping("/r_yhList")
    public ModelAndView r_yhList(HttpServletRequest request, String sn) {
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);//返回SN
        return new ModelAndView("system/Counts/r_yhList", map);
    }

    /**
     * 用户页面加载后 ajsx 调用查询用户列表
     *
     * @param request
     * @param sn
     * @return 返回json
     */
    @ResponseBody
    @RequestMapping("/getr_yhList")
    public Result getr_yhList(HttpServletRequest request, String sn) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = _CountsService.r_yhList(sn);

        return Result.success(list);
    }

    //=================================================地图轨迹==========================================================

    /**
     * 实时数据-运动轨迹页面加载 GET
     *
     * @param request
     * @param sn      设备码
     * @return 返回设备码并加载前台页面
     */
    @RequestMapping("/r_sportGj")
    public ModelAndView r_sportGj(HttpServletRequest request, String sn) {
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);//返回SN
        return new ModelAndView("system/Counts/r_sportGj", map);
    }


    /**
     * 实时数据-运行轨迹页面 POST
     * @param request
     * @param sn
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping("/getr_sportGj")
    public Result getr_sportGj(HttpServletRequest request,String sn,String time)
    {
        Map<String, Object> map = new HashMap<>();

        //用来装围栏信息
        List<Map<String,Object>> dataWL=new ArrayList<Map<String, Object>>();


        String rtJson = "";
        String start = "";//开始时间
        String end = "";//结束时间
        String str_wl = "[]";
        String str_gj = "[";

        try {
            //1.判断查询时间time
            if(time !=null && time.length()!=0) {
                String[] times =time.split(" - ");
                start = times[0] + " 00:00:00";
                end = times[1]+ " 23:59:59";
            }else{
                Date t = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String format = df.format(t);
                start =  df.format(t)+ " 00:00:00";
                end = df.format(t) + " 23:59:59";
            }
            // 2.通过设备查询对应的用户下的围栏信息
            List<Map<String,Object>> data=_CountsService.getWl(sn);

            if(data.size()>0){
                for (int i = 0; i < data.size(); i++) {
                    String pj_str = "[";
                    String fastjson=(String)data.get(i).get("datajson");
                    //将围栏数据JSON格式反序列化
                    JSONArray platformArray = JSON.parseArray(fastjson);
                    //循环获取围栏的定位点
                    for (Object jsonObject : platformArray) {
                        WL platformModel = JSONObject.parseObject(jsonObject.toString(), WL.class);
                        String jd = platformModel.getLongitude(); //经度
                        String wd = platformModel.getLatitude(); //纬度
                        String[] jwdnew=GetLocation.getNewJWD(jd,wd);//转换经纬度（百度API）
                        pj_str += "[" + jwdnew[0] + "," + jwdnew[1] + "]" + ",";
                    }
                    pj_str = pj_str.substring(0, pj_str.length() - 1) + "]";

                    //获取title和datajson重新组装
                    Map<String,Object> _map=new HashMap<>();
                    _map.put("title",(String)data.get(i).get("title"));
                    _map.put("datajson",fastjson);
                    _map.put("data",pj_str);
                    dataWL.add(_map);
                }
                str_wl = TreeUtils.Object2Json(dataWL).toString();
            }


        //3. 获取运动轨迹信息
            List<Map<String,Object>> datas=_CountsService.getGJ(sn,start,end);

            if(datas.size()>0) {
                for (int i = 0; i < datas.size(); i++) {
                    String jd =datas.get(i).get("jd").toString();
                    String wd = datas.get(i).get("wd").toString();
                    String[] jwdnew=GetLocation.getNewJWD(jd,wd);
                    str_gj += "{\"jd\":" + jwdnew[0] + ",\"wd\":" + jwdnew[1]  + "}" + ",";
                }
                str_gj = str_gj.substring(0, str_gj.length() - 1) + "]";
            }else
            {
                str_gj += "]";
            }
            rtJson = "{\"wl\":" + str_wl + ",\"gj\":" + str_gj + "}";
            System.out.println(rtJson);

        } catch (Exception e) {
            rtJson = "{\"wl\":[],\"gj\":[]}";
            e.printStackTrace();
        }

        return Result.success(rtJson);
    }







    //=================================================护理用户==========================================================

    /**
     * 护理用户主页
     *
     * @param request
     * @return View
     */
    @RequestMapping("/c_cuserList")
    public String c_cuserList(HttpServletRequest request) {
        return "system/Counts/c_cuserList";
    }

    /**
     * 护理用户主页
     *
     * @param request
     * @param telephone
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/getc_cuserList")
    public Result getc_cuserList(HttpServletRequest request, String telephone) {

        List<Map<String, Object>> list = _CountsService.getc_cuserList(telephone);
        return Result.success(list);
    }


    /**
     * 护理用户详情 get
     * @param request
     * @param uid 用户id
     * @return 用户绑定信息集合
     */
    @RequestMapping("/c_cuserDetail")
    public ModelAndView c_cuserDetail(HttpServletRequest request, String uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);//返回SN
        return new ModelAndView("/system/Counts/c_cuserDetail", map);
    }

    /**
     * 护理用户详情 post
     * @param request
     * @param uid 用户id
     * @return 用户绑定信息集合
     */
    @ResponseBody
    @RequestMapping("/getc_cuserDetail")
    public Result getc_cuserDetail(HttpServletRequest request,String uid){
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = _CountsService.getc_cuserDetail(uid);
        return Result.success(list);
    }

    /**
     * 护理用户 实时数据 GET
     * @param request
     * @param sn
     * @return
     */
    @RequestMapping("/r_realListView")
    public ModelAndView r_realListView(HttpServletRequest request, String sn) {
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);//返回SN
        return new ModelAndView("/system/Counts/r_realListView", map);
    }



    /**
     * 护理用户 定制消息 GET
     * @param request
     * @param uid 用户id
     * @return 定制消息集合
     */
    @RequestMapping("/c_cuserMsg")
    public ModelAndView c_cuserMsg(HttpServletRequest request,String uid){
        Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        return new ModelAndView("/system/Counts/c_cuserMsg",map);
    }

    /**
     * 护理用户 定制消息 POST
     * @param request
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getc_cuserMsg")
    public  Result getc_cuserMsg(HttpServletRequest request,String uid){
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = _CountsService.getc_cuserMsg(uid);
        return Result.success(list);
    }

}



