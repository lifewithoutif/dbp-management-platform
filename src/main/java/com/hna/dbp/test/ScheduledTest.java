package com.hna.dbp.test;



import com.hna.dbp.eureka.lite.EurekaLiteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class ScheduledTest {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTest.class);

    @Scheduled(cron="0 0/1 * * * ?")
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:"+current.getId());
        LOG.info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
    }
}
