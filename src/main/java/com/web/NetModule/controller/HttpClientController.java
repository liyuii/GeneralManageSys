package com.web.NetModule.controller;

import cn.hutool.http.HttpUtil;
import com.sun.javafx.fxml.builder.URLBuilder;
import com.web.NetModule.service.GrabPageService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@RestController
@RequestMapping("/httpclient")
public class HttpClientController {

    @Autowired
    private GrabPageService grabService;

    @GetMapping("/getwithoutparams")
    public void getwithoutparams(String city) throws IOException {
    CloseableHttpClient client = HttpClientBuilder.create().build();
//        HttpGet get = new HttpGet("https://www.apiopen.top/weatherApi");
        HttpGet get = new HttpGet("http://wthrcdn.etouch.cn/weather_mini?city="+city);
        CloseableHttpResponse execute = client.execute(get);
        HttpEntity entity = execute.getEntity();
        String res = getJsonStringFromGZIP(entity);
//        String str = EntityUtils.toString(entity);
//        String res = conventFromGzip(str);
        System.out.println(res);
    }


    private String getJsonStringFromGZIP(HttpEntity entity) {
        String jsonString = null;
        try {
            InputStream is = entity.getContent();
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            int headerData = getShort(header);
            if (result != -1 && headerData == 0x1f8b) {
                is = new GZIPInputStream(bis);
            } else {
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
//            Log.e("HttpTask", e.toString(), e);
            e.printStackTrace();
        }
        return jsonString;
    }

    private int getShort(byte[] data) {
        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }


    @GetMapping("/getwithparams")
    public void getwithparams() throws IOException, URISyntaxException {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("city","成都"));
        URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("").setParameters(list).build();
        HttpGet get = new HttpGet(uri);

        CloseableHttpResponse execute = client.execute(get);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
    }

    @GetMapping("/postwithoutparams")
    public void postwithoutparams() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://www.apiopen.top/weatherApi?city=成都");


        CloseableHttpResponse execute = client.execute(post);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
    }

    @GetMapping("/postwithparams")
    public void postwithparams() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://www.apiopen.top/weatherApi?city=成都");


        CloseableHttpResponse execute = client.execute(post);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
    }

    @GetMapping("/httpsTest")
    public void httpsTest() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet https = new HttpGet("https://www.apiopen.top/weatherApi?city=成都");
        CloseableHttpResponse execute = client.execute(https);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
    }

    private String conventFromGzip(String str) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in;
        GZIPInputStream gunzip = null;

        in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }

        return out.toString();
    }


    @GetMapping("/jyzxInfo")
    public void jyzxInfo() throws IOException {
        String s = grabService.jyzx_130406();
        System.out.println(s);
    }

    @GetMapping("/httptest")
    public void httpTest() throws IOException {
        //1.get
        String url = "http://www.sjzsggzyjyzx.org.cn/xwzxtz/index.jhtml";
        String reto1 = HttpUtil.get(url);
        //2.post
        String url2 = "http://www.sjzsggzyjyzx.org.cn/queryContent-xwzx.jspx";
        Map<String,Object> map = new HashMap<>();
        map.put("arg1","param1");
        map.put("arg2","param2");
        String reto2 = HttpUtil.post(url2,map);
        //3.post携带headers


    }
}
