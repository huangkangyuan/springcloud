package com.hl.zipkin.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class ImageDemo2 {

    private static void saveImage(String path) {

        try {
            Document doc = Jsoup.connect(path).get();
            Element body = doc.body();
            Elements elements = body.select("img");

            String outputFilePath = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\";
            String src = "";
            HttpURLConnection conn = null;
            InputStream inStream = null;
            byte[] data = null;
            String filePath = null;
            FileOutputStream outStream = null;

            long startTime = new Date().getTime();

            for (Element element : elements) {
                src = element.attr("src");
                System.out.println(path + src);
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

            }
            System.out.println(elements.size());
            System.out.println("读写速度：" + (new Date().getTime() - startTime) + "毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        String path = "http://pic.netbian.com";
        saveImage(path);
    }
}