package com.hl.zipkin.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class QuanMinJsoup {
    public static void main(String[] args) throws Exception {
        //这里是分享地址：
        String url = "https://kg2.qq.com/node/play?s=571cI75H1xb495Eq&shareuid=609c9b812624338a&topsource=a0_pn201001006_z11_u21643941_l0_t1534223843__";
        Connection tempConn = Jsoup.connect(url);
        //模拟浏览器的请求头
        tempConn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        //开始连接HTTP请求。
        Connection.Response demo = tempConn.ignoreContentType(true).method(Connection.Method.GET)
                .execute();
        Document documentDemo = demo.parse();

        Elements scriptElements = documentDemo.getElementsByTag("script");
        String initScriptStr = scriptElements.get(2).toString();
        String jsonStr = initScriptStr.substring(initScriptStr.indexOf("{"), initScriptStr.indexOf("; </script>"));
        //这就获得所有参数最终的json体了。
        System.out.println(jsonStr);
        //这里就是获取该页面的HTML元素。
        System.out.println(jsonStr);
    }
}