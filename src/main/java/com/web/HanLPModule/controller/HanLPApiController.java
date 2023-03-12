package com.web.HanLPModule.controller;

import cn.hutool.http.HttpUtil;
import com.web.HanLPModule.service.HanLPApiService;
import com.web.base.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hanlp/api")
public class HanLPApiController {

    @Resource
    private HanLPApiService hanlpApiService;

    @GetMapping("segment.json")
    public String segmentInvoke(){
        //请求头中的token
        String token="69317e9f9f004d2185e2016f90b851081673183523431token";
        //申请的接口地址
        String url="http://comdo.hanlp.com/hanlp/v21/tok/tok";
        //被推荐各类型文本，作为params参数传入
        Map<String,Object> params=new HashMap<String,Object>();
        //  sentence1-3是 需要进行推荐的文本列表
        String sentence1 ="11月27日，最高人民检察院发布6起正当防卫不捕不诉典型案例，进一步明确正当防卫制度的法律适用，统一司法标准，准确理解把握最高人民法院、最高人民检察院、公安部今年9月联合发布的《关于依法适用正当防卫制度的指导意见》，为促进严格执法公正司法提供有效指引。";
        String sentence2 ="“十三五”以来，我国始终将教育摆在经济社会发展的优先位置，坚持将加强教师队伍建设作为教育事业的基础工作，优化教师发展环境，促进教育优先发展，教师队伍建设取得显著成就，有力支撑起世界最大规模教育体系，源源不断地为我国经济社会发展提供人才和智力支持。";
        String sentence3 = "本次操作面向公开市场业务一级交易商公开招标，中标机构包括国有大型商业银行、股份制银行、城商行、农商行、证券公司等各类金融机构。换入债券既有国有大型商业银行和股份制银行发行的永续债，也有城商行和农商行发行的永续债，体现了对中小银行发行永续债补充资本的支持。";

        //text是想要推荐的内容（需要包含想要获取文本推荐的主题内容）
        params.put("text", "本次操作面向公开市场业务一级交易商公开招标，中标机构包括国有大型商业银行、股份制银行、城商行、农商行、证券公司等各类金融机构。换入债券既有国有大型商业银行和股份制银行发行的永续债，也有城商行和农商行发行的永续债，体现了对中小银行发行永续债补充资本的支持。");     //  第一种：想要推荐法律类型的文本
        //params.put("text", "金融");     //  第二种：想要推荐金融类型的文本
        //params.put("text", "教育");     //  第三种：想要推荐教育类型的文本

        //拼接sentenceArray列表（sentence1 2 3组合）格式需为['','','']
//        params.put("sentenceArray", "['"+sentence1+"','"+sentence2+"','"+sentence3+"']");

        //设置的返回结果的数量，这里返回1条关于推荐的内容文本
//        params.put("size", "1");

        //执行HanLP文本推荐接口，result为返回的结果
        String result = hanlpApiService.doHanlpApi(token,url,params);
        //打印输出文本推荐内容结果
        System.out.println(result);
        return result;
    }
}
