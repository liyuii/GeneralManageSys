package com.web.base.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ttest {

    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(0,"7");
        System.out.println(list.toString());

//        String result = sendGet("https://timor.tech/api/holiday/year/2022/");
////        System.out.println(result);
//        JSONObject json =JSONObject.parseObject(result);
////        List<Map<String,Object>> holiday = (List<Map<String,Object>>)json.get("holiday");
//        Map<String,Object> holiday = (Map<String,Object>)json.get("holiday");
//        Set<Map.Entry<String, Object>> entries = holiday.entrySet();
//        List<WorkDay> works = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : entries) {
//            Object obj = entry.getValue();
//            WorkDay workday = JSONObject.parseObject(JSONObject.toJSONString(obj), WorkDay.class);
//            works.add(workday);
//        }
//        System.out.println(works.size());
//        for(WorkDay day:works){
//            System.out.println(day);
//        }
    }

    public static List<String> getHoliday(String year){
        Calendar cal = Calendar.getInstance();
        int currentYear;
        if(!StringUtils.isEmpty(year)){
            currentYear = Integer.valueOf(year).intValue();
        }else{
            //获取系统当前年
            currentYear = cal.get(Calendar.YEAR);
        }
        cal.clear();
        cal.set(Calendar.YEAR, currentYear);
        Date startDate = cal.getTime();//第一天 Mon Jan 01 00:00:00 CST 2018
        cal.roll(Calendar.DAY_OF_YEAR, -1);
        Date endDate = cal.getTime();//最后一天 Mon Dec 31 00:00:00 CST 2018
        Long step = (endDate.getTime() - startDate.getTime())/ (24 * 60 * 60 * 1000);// 相隔天数  364
        //周末集合----需在前端页面标志，若本身是非工作日，点击则调用删除，否则调用添加，
        List<String> holidayList = new ArrayList<>();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_YEAR, 1);
        for (int i = 0; i < step+1; i++) {
            //判断是否为周末
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                holidayList.add(simple.format(cal.getTime()));
            }
            //加一天
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return holidayList;
    }

    public static List<String> autoGenerateDate(){
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取下一年
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        //设置下一年的开始和结束日期
        String startDate = calendar.get(Calendar.YEAR)+"-01-01";
        String endDate = calendar.get(Calendar.YEAR)+"-12-31";
        try {
            Date startDate2 = sdf.parse(startDate);
            Date endDate2 = sdf.parse(endDate);
            dates.add(startDate);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startDate2);
            //循环出日期存在list
            while(endDate2.after(calStart.getTime())){
                calStart.add(Calendar.DAY_OF_MONTH,1);
                dates.add(sdf.format(calStart.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }


    public static String sendGet(String url) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("contentType", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            // System.out.println(key + "--->" + map.get(key));
            // }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
//			System.out.println("发送GET请求出现异常！" + e);
            result = new StringBuilder("{\"resCode\":\"1\",\"errCode\":\"1001\",\"resData\":\"\"}");
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result.toString();
    }



}
