package com.web.base.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TianYanService {

    public static final String TOKEN_TIANYAN = "b75ff875-884a-4f25-b3fb-3f290e42f403";//最新token
    private HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 1000);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }

    public JSONObject getDwtz(String corpName, int pageNum, int pageSize) throws IOException {//河北省人民政府国有资产监督管理委员会
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
