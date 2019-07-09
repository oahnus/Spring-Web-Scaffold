package com.github.oahnus.scaffold.web.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.*;

import java.io.IOException;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by oahnus on 2019/7/9
 * 14:41.
 */
@Configuration
public class QuartzConfig {

    @Autowired
    ApplicationContext applicationContext;

//    // 1. job
//    @Bean
//    public JobDetail jobDetail() {
//        return JobBuilder.newJob().ofType(SimpleJob.class)
//                .storeDurably()
//                .withIdentity("Simple")
//                .withDescription("Simple Job Description")
//                .build();
//    }

    // 2. job
//    @Bean
//    public JobDetailFactoryBean simpleJobDetail() {
//        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
//        jobDetailFactory.setJobClass(SimpleJob.class);
//        jobDetailFactory.setDescription("Invoke Sample Job service...");
//        jobDetailFactory.setDurability(true);
//        return jobDetailFactory;
//    }

    // 3. cronjob
//    @Bean
//    public JobDetailFactoryBean cronJobDetail() {
//        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
//        jobDetailFactory.setJobClass(CronJob.class);
//        jobDetailFactory.setDescription("Invoke Sample Job service...");
//        jobDetailFactory.setDurability(true);
//        return jobDetailFactory;
//    }

    // 1. trigger
//    @Bean
//    public Trigger trigger(JobDetail job) {
//        return TriggerBuilder.newTrigger().forJob(job)
//                .withIdentity("Quartz_Trigger")
//                .withDescription("Simple trigger")
//                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(10))
//                .build();
//    }

    // 2. trigger
//    @Bean
//    public SimpleTriggerFactoryBean trigger(@Qualifier("simpleJobDetail") JobDetail job) {
//        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
//        trigger.setJobDetail(job);
//        trigger.setRepeatInterval(1000 * 30);
//        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        return trigger;
//
//    }

//    @Bean
//    public CronTriggerFactoryBean cronTrigger(@Qualifier("cronJobDetail")JobDetail job) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail(job);
//        trigger.setCronExpression("0 * * * * ?");
//        return trigger;
//    }


//    // 1. scheduler
//    @Bean
//    public Scheduler scheduler(Trigger trigger, JobDetail job) throws SchedulerException, IOException {
//        StdSchedulerFactory factory = new StdSchedulerFactory();
//        factory.initialize(new ClassPathResource("quartz.properties").getInputStream());
//
//        Scheduler scheduler = factory.getScheduler();
//        scheduler.scheduleJob(job, trigger);
//
//        scheduler.start();
//        return scheduler;
//    }

    // 2. scheduler
//    @Bean
//    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
//        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//        schedulerFactory.setApplicationContext(applicationContext);
//        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
//
//        schedulerFactory.setJobDetails(job);
//        schedulerFactory.setTriggers(trigger);
//        return schedulerFactory;
//    }

    @Bean
    public SchedulerFactoryBean scheduler() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setApplicationContext(applicationContext);
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        return schedulerFactory;
    }

}
