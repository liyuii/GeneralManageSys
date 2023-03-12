package com.web.NetModule.controller;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crawler")
public class JsoupController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     *
     * @throws IOException
     */
    @PostMapping("/geturlinfo")
    public void GetUrlInfo() throws IOException, URISyntaxException {
//        String url = "https://space.bilibili.com/387636363/video?tid=0&page=1&keyword=&order=pubdate";

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("tid","0"));
        list.add(new BasicNameValuePair("page","1"));
        list.add(new BasicNameValuePair("keyword",""));
        list.add(new BasicNameValuePair("order","pubdate"));

        URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("").setParameters(list).build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String html = entity.toString();
        System.out.println(html);
    }

    /**
     *
     * @throws IOException
     */
    @GetMapping("/ciyun")
    public void ciyun() throws IOException {

        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        //指定文本文件路径，生成词频集合
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load("D:\\textcloud.txt");
        //设置图片分辨率
        Dimension dimension = new Dimension(1920,1080);
        //此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 20);
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        //设置背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //设置背景图片
        //wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
        //设置背景图层为圆形
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        //生成词云
        wordCloud.build(wordFrequencyList);
        wordCloud.writeToFile("D:\\wy.png");

    }

}
