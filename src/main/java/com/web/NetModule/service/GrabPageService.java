package com.web.NetModule.service;

import com.web.NetModule.entity.GrabInfo;
import com.web.util.string.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrabPageService {

    @Autowired
    private ApiService apiService;

    /**
     * 根据链接选择执行的方法
     * @param url
     * @param code
     * @return
     * @throws IOException
     */
    public List<GrabInfo> invokeSelect(String url, String code) throws IOException {
        List<GrabInfo> list = new ArrayList<>();
        String method = "jyzx_" + code;

        switch (method){
            case "jyzx_130101":
                list = jyzx_130101(url);
                System.out.println("准备抓取【" + code + "数据...");
                break;
            case "jyzx_130401":
                list = jyzx_hd(url,"邯郸市");
            case "jyzx_130429":
                list = jyzx_hd(url,"永年");
            case "jyzx_130432":
                list = jyzx_hd(url,"广平");
            case "jyzx_130433":
                list = jyzx_hd(url,"馆陶");
            case "jyzx_130471":
                list = jyzx_hd(url,"经济技术开发区");
            case "jyzx_130481":
                list = jyzx_hd(url,"武安");
                break;
            case "jyzx_130406":
//                list = jyzx_130406(url);
                break;
            case "jyzx_130427":
                list = jyzx_130427(url);
                break;
            case "jyzx_130428":
                break;
            default:
                break;
        }
        return list;
    }

    /**
     * 抓取【石家庄-市辖区】的通知公告及办事指南
     * @param url
     * @return
     */
    public List<GrabInfo> jyzx_130101(String url) throws IOException {
        System.out.println("抓取数据中...");
        List<GrabInfo> list = new ArrayList<>();
        int i = 0;
        String data = apiService.httpGetResponse(url);
        Document curDoc = Jsoup.parse(data);//解析网页得到文档对象
        Element ul = curDoc.getElementsByClass("panel-list").get(0);
        Elements lis = ul.getElementsByTag("li");
        for(Element li:lis){
            if(i<5){
                GrabInfo notice = new GrabInfo();
                Element a = li.getElementsByTag("a").get(0);
                if(a != null){
                    String title = a.attr("title");
                    if(!StringUtil.isEmpty(title)){
                        notice.setName(title);
                    }
                    String link = a.attr("href");
                    if(!StringUtil.isEmpty(link)){
                        notice.setLink(link);
                    }
                }
                Element div = li.getElementsByTag("div").get(0);
                if(div != null){
                    notice.setDate(div.html());
                }
                list.add(notice);
            }
            i+=1;
        }
        return list;
    }

    /**
     * 抓取【邯郸-市辖区 | 永年区 | 广平县 | 馆陶县 | 经济技术开发区 | 武安市】的通知公告及办事指南
     * @param url
     * @return
     */
    public List<GrabInfo> jyzx_hd(String url,String city) throws IOException {
        System.out.println("抓取数据中...");
        List<GrabInfo> list = new ArrayList<>();
        int i = 0;
        String data = apiService.httpGetResponse(url);
        Document curDoc = Jsoup.parse(data);//解析网页得到文档对象
        Element divtab = curDoc.getElementsByClass("div").get(0);
        Element ul = divtab.getElementsByTag("ul").get(0);
        Elements lis = ul.getElementsByTag("li");
        for(Element li:lis){
            if(i<5){
                GrabInfo notice = new GrabInfo();
                Element a = li.getElementsByTag("a").get(0);
                if(a != null){
                    String title = a.html();
                    if(!StringUtil.isEmpty(title)){
                        notice.setName(title);
                    }
                    String link = a.attr("href");
                    if(!StringUtil.isEmpty(link)){
                        notice.setLink(link);
                    }
                }
                Element div = li.getElementsByTag("div").get(0);
                if(div != null){
                    notice.setDate(div.html());
                }
                if(notice.getName().contains(city)){
                    list.add(notice);
                }
            }
            i+=1;
        }
        return list;
    }

//    /**
//     * 抓取【邯郸-峰峰矿区】的通知公告及办事指南
//     * @param url
//     * @return
//     */
//    public List<GrabInfo> jyzx_130406(String url) throws IOException {
//        System.out.println("抓取数据中...");
//        List<GrabInfo> list = new ArrayList<>();
//
//        Map<String,String> params = new HashMap<>();
//        params.put("colSym","XXFW_TZGG");
//        params.put("number","7");
//        params.put("sortName","publishAt");
//        params.put("sortOrder","desc");
//        String json = apiService.httpPostResponse(url, params);
//        System.out.println(json);
//        return list;
//    }


    /**
     * 抓取【邯郸-磁县】的通知公告及办事指南
     * @param url
     * @return
     */
    public List<GrabInfo> jyzx_130427(String url) throws IOException {
        System.out.println("抓取数据中...");
        List<GrabInfo> list = new ArrayList<>();
        int i = 0;
        String data = apiService.httpGetResponse2(url);
        Document curDoc = Jsoup.parse(data);//解析网页得到文档对象
        Element tr = curDoc.getElementById("rptblqk_ctl00_row");
        Element td = tr.getElementsByTag("td").get(0);
        Element ul = td.getElementsByTag("ul").get(0);
        Elements lis = ul.getElementsByTag("li");
        for(Element li:lis){
            if(i<5){
                GrabInfo notice = new GrabInfo();
                Element a = li.getElementsByTag("a").get(0);
                if(a != null){
                    String title = a.html();
                    if(!StringUtil.isEmpty(title)){
                        notice.setName(title);
                    }
                    String link = a.attr("href");
                    if(!StringUtil.isEmpty(link)){
                        notice.setLink(link);
                    }
                }
                list.add(notice);
            }
            i+=1;
        }
        return list;
    }

    /**
     * 抓取【邯郸-峰峰矿区】的通知公告及办事指南
     * @return
     */
    public String jyzx_130406() throws IOException {
        System.out.println("抓取数据中...");
        List<GrabInfo> list = new ArrayList<>();

        //步骤一：获取 OWASP_CSRFTOKEN
        String url = "http://116.131.179.226:8888/sszt-zyjyPortal/JavaScriptServlet";
        String header = getHeader(url);
//        Map<String,String> params = new HashMap<>();
//        params.put("colSym","XXFW_TZGG");
//        params.put("number","7");
//        params.put("sortName","publishAt");
//        params.put("sortOrder","desc");
//        String json = apiService.httpPostResponse(url, params);
//        System.out.println(json);
        return header;
    }

    private String getHeader(String url) throws IOException {

        Map<String,String> headers = new HashMap<>();
        headers.put("FETCH-CSRF-TOKEN","1");
        String json = apiService.httpPostResponse2(url, null,headers);
        return json;
    }
}


