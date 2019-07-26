package com.hl.zipkin.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebSiteDemo {
    public static void main(String[] args) throws Exception {
        String path = "http://www.qq.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(path);
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, "UTF-8");

        Document document = Jsoup.parse(html);
        Elements elements = document.select("img");
        String outputFilePath = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\";
        String src = "";
        HttpURLConnection conn = null;
        InputStream inStream = null;
        byte[] data = null;
        String filePath = null;
        FileOutputStream outStream = null;

        int nameIndex = 1;
        for (Element element : elements) {
            src = element.attr("src");
            // new一个URL对象
            if (!src.contains(".jpg")) {
                continue;
            }
            URL url = new URL(path + src);
            // 打开链接
            conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式为"GET"
            conn.setRequestMethod("GET");
            // 超时响应时间为秒
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            inStream = conn.getInputStream();
            // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
            data = readInputStream(inStream);
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            filePath = outputFilePath + System.currentTimeMillis() + ".jpg";
            // 创建输出流
            outStream = new FileOutputStream(new File(filePath));
            // 写入数据
            outStream.write(data);
            // 关闭输出流
            outStream.close();
            System.out.println("已经将第" + nameIndex + "张图片下载到了本地");
            nameIndex++;
            Thread.sleep(10);
        }
        System.out.println("所有图片下载完成");
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}