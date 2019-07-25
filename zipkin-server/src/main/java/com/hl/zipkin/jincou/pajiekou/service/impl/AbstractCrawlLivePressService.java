
package com.hl.zipkin.jincou.pajiekou.service.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public abstract class AbstractCrawlLivePressService {

    private String url;

    public void doTask(String url) throws IOException {
        this.url = url;
        int pageNum = 1;

        while (true) {
            List<PageListPress> newsList = crawlPage(pageNum++);
            // 抓取不到新的内容本次抓取结束
            if (CollectionUtils.isEmpty(newsList)) {
                break;
            }

            for (int i = newsList.size() - 1; i >= 0; i--) {
                PageListPress pageListNews = newsList.get(i);
                System.out.println(pageListNews.toString());

            }

        }
    }

    protected abstract List<PageListPress> crawlPage(int pageNum) throws IOException;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageListPress {
        //新闻详情页面url
        private String href;
        //新闻标题
        private String title;
        //新闻阅读数量
        private int readCounts;
        //新闻发布时间
        private Date createTime;
        //新闻摘要
        private String summary;
    }
}
