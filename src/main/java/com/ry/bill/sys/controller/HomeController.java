package com.ry.bill.sys.controller;

import cn.hutool.json.JSONArray;
import com.ry.bill.sys.service.CountsService;
import com.ry.bill.sys.service.HomeService;
import com.ry.bill.sys.utils.GetLocation;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-03 11:27
 * 主页业务 控制类
 */
@Controller
@RequestMapping("/Home")
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService iCountsService) {
        this.homeService = iCountsService;
    }


    @RequestMapping("/Contact")
    public String Contact(HttpServletRequest request){
        return "system/Home/Contact";
    }

    @ResponseBody
    @RequestMapping("/gasMap")
    public Result gasMap(HttpServletRequest request){

        String retJson = "[]";
        String city_list = "[";
        String amount = "[]";
        try
        {
            //实时经纬度
            List<Map<String, Object>> data=homeService.realJW();

            if (data.size() > 0)
            {
                for (int i = 0; i < data.size(); i++)
                {
                    String text = "";
                    String jd = data.get(i).get("jd").toString();
                    String wd = data.get(i).get("wd").toString();
                    String sn = data.get(i).get("sn").toString();
                    String fall = data.get(i).get("fall").toString();
                    //转换经纬度（百度API）
                    String[] jwdnew= GetLocation.getNewJWD(jd,wd);
                    //得到转换后的经纬度
                    String jd_new = jwdnew[0];
                    String wd_new = jwdnew[1];
                    text += "\"<a style=\\\"margin:0;line-height:1.4;text-indent:2em;text-decoration:none\\\">设备编码：" + sn + "</a><br />";
                    text += "<a href=\\\"javascript:gotoApply(" + sn + ",'010201','实时数据')\\\" style=\\\"color:red; font-size:17px;\\\">点击查看设备数据</a> <br />\"";
                    city_list += "{\"longitude\": " + jd_new + ",\"latitude\": " + wd_new + ",\"fall\": " + fall + ",\"text\":" + text + ",\"sn\":" + sn + "},";
                }
                city_list = city_list.substring(0, city_list.length() - 1) + "]";
            }

            List<Map<String, Object>> datas=homeService.realSUM();

            if (datas.size() > 0) {
                JSONArray jsonArray = TreeUtils.Object2Json(datas);// 将 datatable 转成 json
                amount = jsonArray.toString();
            }
            retJson = "{\"amount\":" + amount + ",\"list\":" + city_list + "}";
        }
        catch (Exception e)
        {
            retJson = "{\"amount\":\"[]\",\"list\":\"[]\"}";
        }
        return Result.success(retJson);
    }


}
