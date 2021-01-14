package com.ry.bill.sys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ry.bill.sys.vo.WL;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;

/**
 * @author CKFuture
 * @since 2020-12-29 11：56
 * 获取地理信息
 */
public class GetLocation {

    public static String getAdd(String log, String lat ) {
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://api.map.baidu.com/geocoder/v2/?location=" + log + "," + lat + "&output=json&ak=WEc8RlPXzSifaq9RHxE1WW7lRKgbid6Y";
        String res = "";
        try {

            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }

        String strjson = JSON.toJSONString(res);


        JSONObject jsonObject = JSONObject.parseObject(res);

        String d = jsonObject.getString("result");

        JSONObject jsonObject1 = JSONObject.parseObject(d);

        String stradd = jsonObject1.getString("formatted_address");
        System.out.println(jsonObject1.getString("formatted_address"));


    /*    JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String arr[] = allAdd.split(",");
        System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);*/

        return stradd;
    }

    /**
     * 利用百度地图API转换经纬度
     * @param jd
     * @param wd
     * @return
     */
    public static String[] getNewJWD(String jd,String wd) {
        String UFDODER_URL = "http://api.map.baidu.com/geoconv/v1/?coords=" + jd + "," + wd + "&from=1&to=5&ak=uXkaHlzdBMy5PejljoGhycgt";
        String res = "";
        try {

            URL url = new URL(UFDODER_URL);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        String strX = "";
        String strY = "";

        JSONObject jsonObject = JSONObject.parseObject(res);

        JSONArray platformArray = jsonObject.getJSONArray("result");
        for (Object _jsonObject : platformArray) {
            Map<String, Object> mmap = JSONObject.parseObject(_jsonObject.toString());

            strX = String.valueOf(mmap.get("x"));
            strY = String.valueOf(mmap.get("y"));

        }

        String[] ss = new String[]{strX, strY};

        return ss;


    }

}
