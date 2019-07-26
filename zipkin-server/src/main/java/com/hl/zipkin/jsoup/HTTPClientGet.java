package com.hl.zipkin.jsoup;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 **/
public class HTTPClientGet {

    public static void main(String[] args) throws IOException {
        //1. 需要先创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2. 指定请求方式
        HttpGet get = new HttpGet("http://www.jd.com");

        //3. 可选的: 封装请求参数
        //3.1 封装请求体
        get.setHeader("user-agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");

        //4. 发送请求
        // CloseableHttpResponse: 封装了响应的所有的内容: 响应行 响应头 响应体
        CloseableHttpResponse response = httpClient.execute(get);

        //5. 获取数据
        //5.1 获取状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        if(statusCode==200){
            //获取响应体的数据
            //在httpClient工具包中已经提供了获取响应体的快捷的方式
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(html);
        }
    }
}