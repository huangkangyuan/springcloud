package com.hl.zipkin.jsoup;
import java.util.List;

import com.hl.zipkin.model.JdBook;
import com.hl.zipkin.util.MYSQLControl;
import com.hl.zipkin.util.UrlFetch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

public class JdongMain {
    //log4j的是使用，不会的请看之前写的文章
    private static final Log logger = LogFactory.getLog(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = HttpClients.createDefault();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //抓取的数据
        List<JdBook> jdBookList = UrlFetch.URLParser(client, url);
        //循环输出抓取的数据
        for (JdBook jd:jdBookList) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeInsert(jdBookList);
    }
}