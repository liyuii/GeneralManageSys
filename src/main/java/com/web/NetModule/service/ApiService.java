package com.web.NetModule.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ApiService {

    private Logger logger = LoggerFactory.getLogger(ApiService.class);

//    @Value("${weather.appcode}")
//    private String authorization;
//
//    @Value("${weather.interval}")
//    private String interval;


    public HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1500);
        HttpConnectionParams.setSoTimeout(httpParams, 1500);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }

    public String httpGetResponse(String url) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = httpClient.execute(httpGet).getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    public String httpGetResponse2(String url) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = httpClient.execute(httpGet).getEntity();
        return EntityUtils.toString(entity, "gbk");
    }

    public String httpPostResponse(String url, Map<String, String> params) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<NameValuePair> form = new ArrayList<NameValuePair>();
            for (String name : params.keySet()) {
                form.add(new BasicNameValuePair(name, params.get(name)));
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, "UTF-8");
            httpPost.setEntity(entity);
        }
//        httpPost.setEntity(new StringEntity(sendJson, ContentType.create("application/json", "utf-8")));
        HttpEntity entity = httpClient.execute(httpPost).getEntity();
        return EntityUtils.toString(entity);
    }

    public String httpPostResponse2(String url, Map<String, String> params,Map<String, String> headers) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if(headers != null){
            Set<Map.Entry<String, String>> map1 = headers.entrySet();
            for (Map.Entry<String, String> maps : map1) {
                String key = maps.getKey();
                String value = maps.getValue();
                httpPost.setHeader(key, value);
            }
        }

        if (params != null) {
            List<NameValuePair> form = new ArrayList<NameValuePair>();
            for (String name : params.keySet()) {
                form.add(new BasicNameValuePair(name, (String)params.get(name)));
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, "UTF-8");
            httpPost.setEntity(entity);
        }
//        httpPost.setEntity(new StringEntity(sendJson, ContentType.create("application/json", "utf-8")));
        HttpEntity entity = httpClient.execute(httpPost).getEntity();
        return EntityUtils.toString(entity);
    }

    public String httpPostResponseJson(String url, String json) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        //参数为json
        StringEntity str = new StringEntity(json,"UTF-8");
        str.setContentEncoding("application/json");
        httpPost.setEntity(str);
//        httpPost.setEntity(new StringEntity(sendJson, ContentType.create("application/json", "utf-8")));
        HttpEntity entity = httpClient.execute(httpPost).getEntity();
        return EntityUtils.toString(entity);
    }

//    /**
//     * 获取用户当前ip
//     * @return
//     * @throws Exception
//     */
//    public String getUserIp() throws Exception {
//        String result = "";
////        String ipinfo = HttpUtil.Get("https://api.ipify.org/?format=json");
//        HttpClient httpClient = getHttpClient();
//        HttpGet httpGet = new HttpGet("https://api.ipify.org/?format=json");
//        result = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), "UTF-8");
//        logger.info("获取的ip信息为：" + result);
//        JSONObject resultJson = JSONObject.parseObject(result);
//        return (String)resultJson.get("ip");
//    }
//
//    /**
//     * 根据ip获取用户所在城市
//     * @param ip
//     * @return
//     * @throws Exception
//     */
//    public JSONObject getLocation(String ip) throws Exception {
////        String ipinfo = HttpUtil.Get("https://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true");
//        HttpClient httpClient = getHttpClient();
//        HttpGet httpGet = new HttpGet("https://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true");
//        String result = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), "UTF-8");
//        JSONObject resultJson = JSONObject.parseObject(result);
//        System.out.println(resultJson.toString());
//        return resultJson;
//    }

//    /**
//     * 根据城市id获取天气情况
//     * @param area
//     * @param code
//     * @param cityId
//     * @return
//     * @throws IOException
//     */
//    public WeatherInrfo getweather(String area,String code,String cityId) throws IOException {
//        String weatherreq = "";
//        //避免大量重复请求，将请求结果保存到数据库
//        tb_weather weather = weatherDal.dbWeather(code,area);
//        //数据库中存在当前城市的天气信息，或者未超过间隔时间，直接使用数据库记录，都不符合再去请求接口
//        if(weather == null){
//            Date date = new Date();
//            String result = weatherrequest(cityId);
//            tb_weather wea = new tb_weather();
//            wea.setGuid(RandomUtil.uuid());
//            wea.setCity(area);
//            wea.setCode(code);
//            wea.setAddtime(date);
//            wea.setUpdatetime(date);
//            wea.setWeather(result);
//            weatherDal.Add(wea);
//            weatherreq = result;
//        }else{
//            if(new Date().getTime() - weather.getUpdatetime().getTime() <= 1000L * 60 * 60 * Integer.parseInt(interval)){
//                System.out.println("从数据库获取【"+ area +"】天气");
//                weatherreq = weather.getWeather();
//            }else{
//                Date date = new Date();
//                String result = weatherrequest(cityId);
//                weather.setWeather(result);
//                weather.setUpdatetime(date);
//                weatherDal.update(weather);
//                weatherreq = result;
//            }
//        }
//        WeatherInrfo info =  getWeatherInfo(weatherreq);
//        return info;
//    }
//
//    /**
//     * 将天气接口返回的信息封装成对象，方便使用
//     * @param response
//     * @return
//     */
//    private WeatherInrfo getWeatherInfo(String response){
//        JSONObject jsonObject = JSONObject.parseObject(response);
//        JSONObject data = (JSONObject)jsonObject.get("data");
//        JSONObject citys = (JSONObject)data.get("city");
//        JSONArray forcast = (JSONArray)data.get("forecast");
//
//        WeatherInrfo.City cit = JSON.parseObject(JSON.toJSONString(citys), WeatherInrfo.City.class);
//        List<WeatherInrfo.Forcast> fore = JSONObject.parseArray(JSON.toJSONString(forcast), WeatherInrfo.Forcast.class);
//
//        WeatherInrfo wea = new WeatherInrfo();
//        wea.setCity(cit);
//        wea.setForcast(fore);
//        return wea;
//    }
//
//    /**
//     * 请求天气接口
//     * @param cityId
//     * @return
//     * @throws IOException
//     */
//    private String weatherrequest(String cityId) throws IOException {
//        System.out.println("从接口获取天气信息");
//        HttpClient httpClient = getHttpClient();
//        HttpPost post = new HttpPost("http://aliv13.data.moji.com/whapi/json/alicityweather/briefforecast6days");
//        // 设置header
//        post.setHeader("Authorization", "APPCODE " + authorization);
//        //设置请求参数
//        List<NameValuePair> params=new ArrayList<NameValuePair>();
//        //建立一个NameValuePair数组，用于存储欲传送的参数
//        params.add(new BasicNameValuePair("cityId",cityId));
//        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        // 获取响应
//        HttpResponse response = httpClient.execute(post);
//        HttpEntity entity = response.getEntity();
//        return EntityUtils.toString(entity, "utf-8");
//    }
}
