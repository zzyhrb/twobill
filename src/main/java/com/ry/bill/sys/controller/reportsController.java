package com.ry.bill.sys.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONArray;
import com.ry.bill.sys.service.ReportsService;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import com.ry.bill.sys.vo.reportVo;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/Reports")
public class reportsController {


    private powerService _powerService;
    private ReportsService _ReportsService;

    @Autowired
    public reportsController(powerService ipowerService,ReportsService ReportsService)
    {
        this._powerService = ipowerService;
        this._ReportsService=ReportsService;
    }


    @RequestMapping("/equiList")
    public String equiList(HttpServletRequest request){
        return "system/reports/equiList";
    }

    @ResponseBody
    @RequestMapping("/getequiList")
    public Result getequiList(HttpServletRequest request,String datetime,reportVo _reportVo){

        String start="";
        String end="";

        if ("".equals(datetime))
        {
            DateTime dateTimes = DateTime.now(); ;
            dateTimes.year();
            dateTimes.month();
            start=dateTimes.year()+"-"+dateTimes.month()+"-01";
            end=dateTimes.year()+"-"+dateTimes.month()+"-30";
        }else
        {
            String[] times =datetime.split(" - ");
            start = times[0] + "-01";
            end = times[1]+"-30";
        }

        _reportVo.setStart(start);
        _reportVo.setEnd(end);


        List<String> newName = new ArrayList();
        List<String> newMode = new ArrayList();
        int onlineCount = 0;
        int nonlineCount = 0;
        int conCount = 0;
        int comonlineCount = 0;
        int comnonlineCount = 0;
        int comconCount = 0;



        String list="<tbody>";
        list += "<tr>";
        list += "<td style=\"text-align : center;\" colspan=\"2\" rowspan=\"2\">机构名称</td>";
        list += "<td style=\"text-align : center;\" colspan=\"2\" rowspan=\"2\">类别</td>";
        list += "<td colspan=\"15\" style=\"text-align : center;\">" + datetime + "</td>";
        list += "</tr>";
        list += "<tr>";
        list += "<td colspan=\"5\" style=\"text-align : center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线(个)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
        list += "<td colspan=\"5\" style=\"text-align : center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未在线(个)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
        list += "<td colspan=\"5\" style=\"text-align : center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计(个)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
        list += "</tr>";

        List<Map<String,Object>> powersList=_powerService.getagency();

        if (powersList.size() > 0)
        {
            String pname = powersList.get(0).get("name").toString();

            List<Map<String,Object>> listSum= _ReportsService.getSUM(_reportVo);
            JSONArray jsonArray= TreeUtils.Object2Json(listSum);

            String json = jsonArray.toString();


            if (listSum.size() > 0)
            {
                List<Map<String,Object>> datas1=_ReportsService.getfequi(_reportVo);
                String json1 = TreeUtils.Object2Json(datas1).toString();

                int count = listSum.size();
                int count1 = datas1.size();
                String counts =String.valueOf(count + count1);

                list += "<tr><td style=\"text-align : center;\" rowspan=\"" + counts + "\">" + pname + "</td>";
                for (int i = 0; i < listSum.size(); i++)
                {
                    String name = (String)listSum.get(0).get("pname");
                    String stype = (String)listSum.get(0).get("stype");
                    String snmode = (String)listSum.get(0).get("snmode");
                    String online = (String)listSum.get(0).get("online");
                    String nonline = (String)listSum.get(0).get("nonline");
                    String con = (String)listSum.get(0).get("con");
                    if (newName.size() == 0)
                    {
                        newName.add(name);
                        newMode.add(stype);
                        if (stype !=null && snmode !=null)
                        {
                            // datas 字符串中包含 name 的数量

                            Pattern p = Pattern.compile(json);
                            Matcher m = p.matcher(name);
                            m.groupCount();
                            int string1=Pattern.compile(json).matcher(name).groupCount()+1;
                            int string2 =Pattern.compile(json).matcher("{\"pname\":\"" + name + "\",\"stype\":\"" + stype + "\"").groupCount();

                            list += "<td style=\"text-align : center;\" rowspan=\"" +Integer.toString(string1)+ "\">" + name + "</td>";
                            list += "<td style=\"text-align : center;\" rowspan=\"" + Integer.toString(string2) + "\">" + stype + "</td>";
                            list += "<td style=\"text-align : center;\">" + snmode + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + online + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + nonline + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + con + "</td>";
                            list += "</tr>";
                            onlineCount = onlineCount + Integer.parseInt(online);
                            nonlineCount = nonlineCount + Integer.parseInt(nonline);
                            conCount = conCount + Integer.parseInt(con);
                            comonlineCount = comonlineCount + Integer.parseInt(online);
                            comnonlineCount = comnonlineCount + Integer.parseInt(nonline);
                            comconCount = comconCount + Integer.parseInt(con);
                        }
                        else
                        {
                            list += "<td style=\"text-align : center;\" rowspan=\"2\">" + name + "</td>";
                            list += "<td style=\"text-align : center;\" rowspan=\"1\"> -- </td>";
                            list += "<td style=\"text-align : center;\"> -- </td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\"> -- </td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\"> -- </td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\"> -- </td>";
                            list += "</tr>";
                        }
                    }
                    else
                    {
                        if (newName.get(0) == name)
                        {
                            if (newMode.get(0) == stype)
                            {
                                list += "<tr>";
                                list += "<td style=\"text-align : center;\">" + snmode + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + online + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + nonline + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + con + "</td>";
                                list += "</tr>";
                                onlineCount = onlineCount + Integer.parseInt(online);
                                nonlineCount = nonlineCount + Integer.parseInt(nonline);
                                conCount = conCount + Integer.parseInt(con);
                                comonlineCount = comonlineCount + Integer.parseInt(online);
                                comnonlineCount = comnonlineCount + Integer.parseInt(nonline);
                                comconCount = comconCount + Integer.parseInt(con);
                            }
                            else
                            {
                                int string2 =Pattern.compile(json).matcher("{\"pname\":\"" + name + "\",\"stype\":\"" + stype + "\"").groupCount();
                                list += "<tr>";
                                list += "<td style=\"text-align : center;\" rowspan=\"" + String.valueOf(string2) + "\">" + stype + "</td>";
                                list += "<td style=\"text-align : center;\">" + snmode + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + online + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + nonline + "</td>";
                                list += "<td colspan=\"5\" style=\"text-align : center;\">" + con + "</td>";
                                list += "</tr>";
                                onlineCount = onlineCount + Integer.parseInt(online);
                                nonlineCount = nonlineCount + Integer.parseInt(nonline);
                                conCount = conCount + Integer.parseInt(con);
                                comonlineCount = comonlineCount + Integer.parseInt(online);
                                comnonlineCount = comnonlineCount + Integer.parseInt(nonline);
                                comconCount = comconCount + Integer.parseInt(con);
                            }
                        }
                        else
                        {
                            list += "<tr>";
                            list += "<td colspan=\"2\" style=\"text-align : center;\">&nbsp;合计</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(onlineCount) + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(nonlineCount) + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(conCount) + "</td>";
                            list += "</tr>";
                            int string1=Pattern.compile(json).matcher(name).groupCount()+1;
                            int string2 =Pattern.compile(json).matcher("{\"pname\":\"" + name + "\",\"stype\":\"" + stype + "\"").groupCount();
                            list += "<tr>";
                            list += "<td style=\"text-align : center;\" rowspan=\"" + String.valueOf(string1) + "\">" + name + "</td>";
                            list += "<td style=\"text-align : center;\" rowspan=\"" + String.valueOf(string2) + "\">" + stype + "</td>";
                            list += "<td style=\"text-align : center;\">" + snmode + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + online + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + nonline + "</td>";
                            list += "<td colspan=\"5\" style=\"text-align : center;\">" + con + "</td>";
                            list += "</tr>";
                            onlineCount = 0 + Integer.parseInt(online);
                            nonlineCount = 0 + Integer.parseInt(nonline);
                            conCount = 0 + Integer.parseInt(con);
                            comonlineCount = comonlineCount + Integer.parseInt(online);
                            comnonlineCount = comnonlineCount + Integer.parseInt(nonline);
                            comconCount = comconCount + Integer.parseInt(con);
                        }
                        newName.clear();
                        newMode.clear();
                        newName.add(name);
                        newMode.add(stype);
                    }
                }
                list += "<tr>";
                list += "<td colspan=\"2\" style=\"text-align : center;\">&nbsp;合计</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(onlineCount) + "</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(nonlineCount) + "</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(conCount) + "</td>";
                list += "</tr>";
                list += "<tr>";
                list += "<td style=\"text-align : center;\" colspan=\"4\">合计</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(comonlineCount) + "</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(comnonlineCount) + "</td>";
                list += "<td colspan=\"5\" style=\"text-align : center;\">" + String.valueOf(comconCount) + "</td>";
                list += "</tr>";
                list += "</tbody>";
            }
            else
            {
                list += "<tr>";
                list += "<td style=\"text-align : center;\" colspan=\"19\">未查询到销售设备信息</td>";
                list += "</tr>";
                list += "</tbody>";
            }
        }





        return Result.success(list);
    }

}
