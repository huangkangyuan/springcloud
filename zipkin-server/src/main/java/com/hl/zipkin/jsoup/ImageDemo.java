package com.hl.zipkin.jsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ImageDemo {

    private static void Get_Url(String htmlUrl, String path) {

        try {
            Document doc = Jsoup.connect(htmlUrl).get();
            Element body = doc.body();
            Elements elements = body.select("img");
            String src = "";
            for (Element element : elements) {
                src = element.attr("src");
                System.out.println(path + src);
            }
            System.out.println("elements-size: " + elements.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "http://pic.netbian.com";
        Get_Url(path, path);
    }
}