package com.ry.bill.sys.controller;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ry.bill.sys.service.powerService;
import com.ry.bill.sys.service.vueappService;
import com.ry.bill.sys.utils.Result;
import com.ry.bill.sys.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2021-01-04 10:14
 * @description 手机APP接口
 */
@RestController
@RequestMapping("/vueapp")
public class vueappController {
    
    private vueappService vueappService;

    /***
     *  String 是不可变的对象, 因此在每次对 String 类型进行改变的时候其实都等同于生成了一个新的 String 对象，
     *  然后将指针指向新的 String 对象，这样不仅效率低下，而且大量浪费有限的内存空间，
     *  所以经常改变内容的字符串最好不要用 String
     */
    private StringBuffer stringBuffer=null;
    private int icount=0;
    private String rtJson="";

    //构造函数
    @Autowired
    public vueappController(vueappService ivueappService ) {
        this.vueappService = ivueappService;
    }


    /**
     * 用户登录方法 通过用户名以及密码登录
     * @param request
     * @param telephone 用户名
     * @param password 密码
     * @return 返回结果集  {"id":2,"telephone":"18745719113","apassword":"1"}
     * @testUrl /vueapp/login
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam("telephone") String telephone, @RequestParam("password") String password) {

        String id="";
        //具体业务逻辑
        List<Map<String, Object>> list = vueappService.login(telephone, password);
        if(list.size()>0){ id=list.get(0).get("id").toString(); }

        stringBuffer=new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("'code':0");
        stringBuffer.append(",'msg':'登录成功'");
        stringBuffer.append(",'id':'"+id+"'");
        stringBuffer.append(",'telephone':'"+ telephone + "'");
        stringBuffer.append(",'password':'"+ password + "'");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    /**
     * 微信登录
     * @param unionid 微信唯一标识
     * @return
     * @testUrl /vueapp/wxlogin
     */
    @ResponseBody
    @RequestMapping(value = "/wxlogin", method = RequestMethod.POST)
    public String wxlogin(@RequestParam("unionid") String unionid) {
        //具体业务逻辑
        List<Map<String, Object>> list = vueappService.wxlogin(unionid);
        JSONArray jsonArray = TreeUtils.Object2Json(list);

        return jsonArray.toString();
    }


    /**
     * 查询所有utype使用类型 (uid为0的以及用户自定义的)
     * @param id 用户id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getutype", method = RequestMethod.POST)
    public String getutype(@RequestParam("id") String id){
        List<Map<String, Object>> list = vueappService.getutype(id);
        JSONArray jsonArray = TreeUtils.Object2Json(list);
        return jsonArray.toString();
    }

    /**
     * 自定义utype类型
     * @param id
     * @param tname
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setutype", method = RequestMethod.POST)
    public String setutype(@RequestParam("id") String id,@RequestParam("tname") String tname){
        int icount=vueappService.setutype(id,tname);
        if (icount > 0)
        {
            rtJson = "{'code': 0, 'msg': 'success'}";  // 修改成功
        }
        return rtJson;
    }

















}
