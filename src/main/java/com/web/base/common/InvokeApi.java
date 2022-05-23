package com.web.base.common;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.ExcelOptModule.entity.Response;
import com.web.ExcelOptModule.entity.StudentEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvokeApi {

    @Autowired
    private static TianYanService tianYanService;

    public static final String TOKEN_TIANYAN = "b75ff875-884a-4f25-b3fb-3f290e42f403";//最新token

    public static List<Response> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        JSONObject relation = getDwtz("河北省人民政府国有资产监督管理委员会",1,20);//河北省人民政府国有资产监督管理委员会
        JSONObject result1 = (JSONObject)relation.get("result");

        if(result1!=null){
            JSONArray items = result1.getJSONArray("items");
            for(int i=0;i<items.size();i++) {
                System.out.println(items.getJSONObject(i).get("name"));
                Response response = JSONObject.parseObject(JSONObject.toJSONString(items.getJSONObject(i)), Response.class);
                list.add(response);
            }
            System.out.println(list.size());
            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(
                    new ExportParams("查询结果","对外投资", ExcelType.XSSF), Response.class,list
            );
            FileOutputStream fos = new FileOutputStream("C:/Users/86137/Documents/excels/test/res.xlsx");
            workbook.write(fos);
        }
    }

//    public void getAllResult(HttpServletRequest request) throws Exception {
//        JSONObject relation = tianYanService.getDwtz("廊坊荣森水利工程有限公司",1,20);//河北省人民政府国有资产监督管理委员会
//        JSONObject result1 = (JSONObject)relation.get("result");
////        digui(result1);
//
//		if(result1!=null){
//			JSONArray items = result1.getJSONArray("items");
//			for(int i=0;i<items.size();i++) {
//				System.out.println(items.getJSONObject(i).get("name"));
//			}
//		}
//    }

    private void digui(JSONObject result1) throws Exception {
        if(result1 == null){
            return;
        }
        JSONArray items = result1.getJSONArray("items");
        int total = (int)result1.get("total");
        for(int i=0;i<items.size();i++) {
//			System.out.println(items.getJSONObject(i).get("name"));
//            JSONObject relation = tianYanService.getDwtz((String)items.getJSONObject(i).get("name"),1,20);//河北省人民政府国有资产监督管理委员会
            String name = (String)items.getJSONObject(i).get("name");
            //查询所有分页信息
            for(int j=0;j*20 < total;j++){
                JSONObject relation = getDwtz(name,j+1,20);
                JSONObject results = (JSONObject)relation.get("result");
                if(results!=null){
                    JSONArray item = result1.getJSONArray("items");
                    for(int k=0;k<item.size();k++) {
                        System.out.println(item.getJSONObject(k).get("name"));
                        Response response = JSONObject.parseObject(JSONObject.toJSONString(item.getJSONObject(k)), Response.class);
                        list.add(response);
                    }
                    System.out.println(list.size());
                }
                digui(results);
            }
        }
    }

    private static HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 1000);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }

    public static JSONObject getDwtz(String corpName, int pageNum, int pageSize) throws IOException {//河北省人民政府国有资产监督管理委员会
//        Map<String, Object> map = new HashMap<>();
        String url = "https://open.api.tianyancha.com/services/open/ic/inverst/2.0?pageSize=" + pageSize + "&keyword=" + corpName + "&pageNum=" + pageNum;
        HttpClient httpClient = getHttpClient();
        HttpGet get = new HttpGet(url);
        // 设置header
        get.setHeader("Authorization", TOKEN_TIANYAN);
        // 设置类型
        HttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        JSONObject map = JSONObject.parseObject(result);
        return map;
    }

}
