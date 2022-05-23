package com.web.NetModule.controller;

import com.sun.javafx.fxml.builder.URLBuilder;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/httpclient")
public class HttpClientController {

    @GetMapping("/getwithoutparams")
    public void getwithoutparams() throws IOException {
    CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://www.apiopen.top/weatherApi");
        CloseableHttpResponse execute = client.execute(get);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
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
}
