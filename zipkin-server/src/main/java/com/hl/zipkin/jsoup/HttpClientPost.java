package com.hl.zipkin.jsoup;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 **/
public class HttpClientPost {

    public static void main(String[] args) throws Exception {
        //1. 获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2. 指定请求方式
        HttpPost httpPost = new HttpPost("http://www.jd.com");

        //3. 封装请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

        list.add(new BasicNameValuePair("username","kangyuan"));
        list.add(new BasicNameValuePair("age","22"));

        HttpEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);
        //3.1 请求体

        //4. 执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //5. 获取数据
        //5.1 获取状态码
        int statusCode = response.getStatusLine().getStatusCode();
        //5.2 获取响应头的数据
        Header[] headers = response.getHeaders("content-type");

        String value = headers[0].getValue();
        System.out.println(statusCode +"  "+ value);

        //5.3 获取响应体数据
        String html = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(html);
    }
}