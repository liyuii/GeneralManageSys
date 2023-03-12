package com.web.NetModule.controller;

import com.alibaba.fastjson.JSON;
import com.web.ExcelOptModule.entity.User;
import org.apache.commons.lang3.RandomUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/es")
public class EsApiController {

    private final String ES_INDEX = "document_index";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 添加索引测试
     */
    @GetMapping("/addindex")
    public void addindex() throws IOException {
        //1、构建 创建索引的请求
        CreateIndexRequest request = new CreateIndexRequest(ES_INDEX);//索引名
        //2、客户端执行请求,获取响应
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("创建成功，创建的索引名为：" + response.index());
    }

    /**
     * 获取索引测试
     */
    @GetMapping("/getIndex")
    public void getIndex() throws IOException {
        //1、构建 获取索引的请求
        GetIndexRequest request = new GetIndexRequest(ES_INDEX);
        //2、客户端判断该索引是否存在
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("该索引是否存在："+exists);
    }

    /**
     * 添加文档测试
     */
    @GetMapping("/addDoc")
    public void addDoc() throws IOException {
        List<User> list = new ArrayList<>();
        User user1 = new User("1","小李","123456789",20,1000,"清华");
        User user2 = new User("2","小张","123456789",22,1000,"北大");
        User user3 = new User("3","小赵","123456789",24,1000,"科大");
        User user4 = new User("3","小王","123456789",24,2000,"科大");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        int count = 1;
        for(User user:list){
            count += 1;
            IndexRequest request = new IndexRequest(ES_INDEX);
            request.id(String.valueOf(count));
            request.source(JSON.toJSONString(user), XContentType.JSON);
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            System.out.println(indexResponse.toString());
            System.out.println(indexResponse.status());
        }

    }

    /**
     * 搜索文档测试
     */
    @GetMapping("/search")
    public void search() throws IOException {
        SearchRequest request = new SearchRequest(ES_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        List<String> list = new ArrayList<>();
        list.add("qh");
        list.add("bd");
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("school","qh"))
                .should(QueryBuilders.termQuery("school","bd"))
                .must(QueryBuilders.termQuery("deposit",1000));
//                .minimumShouldMatch(1);

        sourceBuilder.query(builder);
        request.source(sourceBuilder);
        SearchResponse resp = restHighLevelClient.search(request, RequestOptions.DEFAULT);
//        System.out.println(JSON.toJSONString(resp.getHits()));
        for(SearchHit hit:resp.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    /**
     * 聚合测试
     */
    @GetMapping("/group")
    public void group() throws IOException {
        SearchRequest request = new SearchRequest(ES_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders.count("count_name").field("deposit"));
        request.source(sourceBuilder);
        SearchResponse resp = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = resp.getAggregations();
        System.out.println(JSON.toJSONString(aggregations.getAsMap().get("count_name")));
    }

    /**
     * 聚合测试
     */
    @GetMapping("/complexgroup")
    public void complexgroup() throws IOException {
        SearchRequest request = new SearchRequest(ES_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //制定检索条件
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                .terms("school_group").field("school.keyword")
                .subAggregation(AggregationBuilders.sum("sum_deposit").field("deposit"));

        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .aggregation(termsAggregationBuilder);
        request.source(sourceBuilder);
        SearchResponse resp = restHighLevelClient.search(request, RequestOptions.DEFAULT);
//        Aggregations aggregations = resp.getAggregations();
        listAggregations(resp);
    }

    private static void listAggregations(SearchResponse searchResponse) {
        // 处理聚合查询结果
        Aggregations aggregations = searchResponse.getAggregations();
        Terms byShopAggregation = aggregations.get("school_group");

        // 遍历terms聚合结果
        for (Terms.Bucket bucket : byShopAggregation.getBuckets()) {
            String color = bucket.getKeyAsString();
            long colorCount = bucket.getDocCount();
            System.out.print("\n学校 " + color + "\n数量 : " + colorCount);

            // 根据avg_price聚合名字，获取嵌套聚合结果
            Sum sum = bucket.getAggregations().get("sum_deposit");
            // 获取平均价格
            double sumPrice = sum.getValue();
            System.out.print(" ; 总存款 : " + sumPrice);

//            // 根据avg_price聚合名字，获取嵌套聚合结果
//            Avg avg = bucket.getAggregations().get("avg_price");
//            // 获取平均价格
//            double avgPrice = avg.getValue();
//            System.out.println(" ; 平均价格 : " + avgPrice);
        }
    }
}
