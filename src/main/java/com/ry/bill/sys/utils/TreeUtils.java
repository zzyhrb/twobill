package com.ry.bill.sys.utils;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {
    private static List<Map<String, Object>> treeDataList = new ArrayList<>();

    /***
     * @author ckfuture
     * @since 2020-12-24 20:38
     * @param treeList
     * @param parentId
     * @param id
     * @param title
     * @param parentIdName
     * @param param
     * @return
     */
    public static JSONArray treeRecursionDataList(List<Map<String, Object>> treeList, BigInteger parentId, String id, String title, String parentIdName, List<String> param) {
        JSONArray childMenu = new JSONArray();
        Map<String, Object> treeMap = new HashMap<String, Object>();
        for (Map<String, Object> map : treeList) {
            map.put("title", map.get(title));
            map.put("id", map.get(id));
            if (param != null) {
                Map<String, Object> basicDataMap = new HashMap<>();
                for (String str : param) {
                    basicDataMap.put(str, map.get(str));
                }
                map.put("basicData", basicDataMap);
            }
            map.put("parentId", map.get(parentIdName));
            JSONObject jsonMenu = JSONObject.parseObject(JSON.toJSONString(map));
            BigInteger menuId = new BigInteger(map.get(id).toString().trim());
            int pid = jsonMenu.getIntValue("parentId");
            if (parentId.compareTo(BigInteger.valueOf(pid)) == 0) {
                JSONArray c_node = treeRecursionDataList(treeList, menuId, id, title, parentIdName, param);
                jsonMenu.put("children", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }


    public static JSONArray Object2Json(List<Map<String, Object>> List) {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> treeMap = new HashMap<String, Object>();
        for (Map<String, Object> map : List) {
            JSONObject jsonMenu = JSONObject.parseObject(JSON.toJSONString(map));
            jsonArray.add(jsonMenu);
        }

        return jsonArray;
    }



}
