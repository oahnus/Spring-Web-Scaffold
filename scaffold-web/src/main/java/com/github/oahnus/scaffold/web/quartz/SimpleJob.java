package com.github.oahnus.scaffold.web.quartz;

import com.github.oahnus.scaffold.common.utils.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2019/7/9
 * 14:40.
 */
@Component
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.print(Thread.currentThread().getName() + " ");
        System.out.print("Run Simple Job ");
        System.out.println(DateUtils.now());
    }
}
