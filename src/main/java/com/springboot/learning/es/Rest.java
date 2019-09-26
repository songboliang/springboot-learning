package com.springboot.learning.es;



import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;

import static org.springframework.boot.autoconfigure.h2.H2ConsoleProperties.*;

/**
 * Elasticserach RestClient示例
 * @author fendo
 *
 */
public class Rest {

    private static RestClient restClient;



    public void getRestClient(){

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        restClient = RestClient.builder(new HttpHost("20.26.38.120",9200,"http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();
    }

    @Before
    public void getRest(){
        restClient = RestClient.builder(new HttpHost("20.26.38.118", 9200, "http")).build();
    }



    /**
     * 查看api信息
     * @throws Exception
     */
    @Test
    public void CatApi() throws Exception{
        String method = "GET";
        String endpoint = "_count?pretty";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void CreateIndex() throws Exception{
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     * @throws Exception
     */
    @Test
    public void CreateDocument()throws Exception{

        String method = "PUT";
        String endpoint = "/megacorp/employee/3";
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"first_name\" :  \"Douglas\",\n" +
                        "    \"last_name\" :   \"Fir\",\n" +
                        "    \"age\" :         35,\n" +
                        "    \"about\":        \"I like to build cabinets\",\n" +
                        "    \"interests\":  [ \"forestry\" ]\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint, Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 获取文档
     * @throws Exception
     */
    @Test
    public void getDocument()throws Exception{
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 删除文档
     */
    @Test
    public void test3() throws Exception {


        String method = "DELETE";
        String endpoint = "/megacorp/employee/3";
        Response response = restClient.performRequest(method,endpoint);
    }


    /**
     * 查询所有数据
     * @throws Exception
     */
    @Test
    public void QueryAll() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 全文搜索
     * @throws Exception
     */
    @Test
    public void QueryByText() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 短语搜索
     * @throws Exception
     */
    @Test
    public void QueryByPhrase() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"query\" : {\n" +
                "        \"match_phrase\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 高亮搜索
     * @throws Exception
     * 当我们运行这个语句时，会命中与之前相同的结果，但是在返回结果中会有一个新的部分叫做highlight，
     * 这里包含了来自about字段中的文本，并且用<em></em>来标识匹配到的单词。
     */
    @Test
    public void QueryByHighlight() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"query\" : {\n" +
                "        \"match_phrase\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"highlight\": {\n" +
                "        \"fields\" : {\n" +
                "            \"about\" : {}\n" +
                "        }\n" +
                "    }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     *
     * 聚合
     * @throws Exception
     */

    @Test
    public void QueryByAggregations() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"aggs\": {\n" +
                "    \"all_interests\": {\n" +
                "      \"terms\": { \"field\": \"interests\" }\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void QueryByAggregations1() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"aggs\" : {\n" +
                "        \"all_interests\" : {\n" +
                "            \"terms\" : { \"field\" : \"interests\" },\n" +
                "            \"aggs\" : {\n" +
                "                \"avg_age\" : {\n" +
                "                    \"avg\" : { \"field\" : \"age\" }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 查询所有数据
     * @throws Exception
     */
    @Test
    public void QueryByConditions() throws Exception {
        String method = "POST";
        String endpoint = "/megacorp/employee/_search?q=last_name:Smith";
//        HttpEntity entity = new NStringEntity("{\n" +
//                "  \"query\": {\n" +
//                "    \"match_all\": {}\n" +
//                "  }\n" +
//                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     * @throws Exception
     */
    @Test
    public void QueryByField() throws Exception {
        String method = "GET";
        String endpoint = "/megacorp/employee/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"user\": \"kimchy\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     * @throws Exception
     */
    @Test
    public void UpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "	}\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    @Test
    public void GeoBoundingBox() throws IOException {
        String method = "POST";
        String endpoint = "/attractions/restaurant/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  },\n" +
                "  \"post_filter\": {\n" +
                "    \"geo_bounding_box\": {\n" +
                "      \"location\": {\n" +
                "        \"top_left\": {\n" +
                "          \"lat\": 39.990481,\n" +
                "          \"lon\": 116.277144\n" +
                "        },\n" +
                "        \"bottom_right\": {\n" +
                "          \"lat\": 39.927323,\n" +
                "          \"lon\": 116.405638\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    /**
     * 查询集群健康状态
     * @throws Exception
     */
    @Test
    public void QueryByCluster() throws Exception {
        String method = "GET";
        String endpoint = "/_cluster/health\n";
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}

