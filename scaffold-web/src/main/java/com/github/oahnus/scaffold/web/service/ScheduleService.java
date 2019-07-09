package com.github.oahnus.scaffold.web.service;

import com.github.oahnus.scaffold.web.quartz.CronJob;
import com.github.oahnus.scaffold.web.quartz.JobScheduleCreator;
import com.github.oahnus.scaffold.web.quartz.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by oahnus on 2019/7/9
 * 16:00.
 */
@Service
public class ScheduleService {
    @Autowired
    JobScheduleCreator jobScheduleCreator;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public void startAllScheduleJobs() throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(SimpleJob.class)
                .storeDurably()
                .withIdentity("SimpleJob")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("SimpleQuartzTrigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(30))
                .build();

        JobDetail cronJobDetail = JobBuilder.newJob()
                .ofType(CronJob.class)
                .storeDurably()
                .withIdentity("CronJob")
                .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .forJob(cronJobDetail)
                .withIdentity("CronQuartzTrigger")
                .withSchedule(cronSchedule("0 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.scheduleJob(cronJobDetail, cronTrigger);
    }
}
