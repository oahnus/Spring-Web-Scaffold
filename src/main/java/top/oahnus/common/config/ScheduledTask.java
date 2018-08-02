package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by oahnus on 2017/11/28
 * 14:58.
 */
@Component
@Slf4j
public class ScheduledTask {


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时任务
     *  fixedRate = 5000 上一次执行点后5秒开始执行
     *  fixedDelay = 5000 上一次执行完成后5秒开始执行
     *  initialDelay = 1000 第一次延迟1秒执行
     *  cron cron规则执行
     */
//    @Scheduled(fixedRate = 60000) // 每分钟
    public void printTime() {
        // do something
    }

    /**
     * 每天零点
     */
//    @Scheduled(cron = "0 0 0 * * *")
    public void snapshot() {
        // do something
    }
}
