package com.hl.zipkin;

import com.hl.zipkin.jincou.pajiekou.service.CrawlerJinSeLivePressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulePressTrigger {

    @Autowired
    private CrawlerJinSeLivePressService crawlerJinSeLivePressService;

    /**
    * 定时抓取金色财经的新闻
    */
    @Scheduled(initialDelay = 1000, fixedRate = 600 * 1000)
    public void doCrawlJinSeLivePress() {
      //  log.info("开始抓取金色财经新闻, time:" + new Date());
        try {
            crawlerJinSeLivePressService.start();
        } catch (Exception e) {
          //  log.error("本次抓取金色财经新闻异常", e);
        }
      //  log.info("结束抓取金色财经新闻, time:" + new Date());
    }
}
