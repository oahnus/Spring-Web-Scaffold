package com.github.oahnus.scaffold.web.quartz;

import com.github.oahnus.scaffold.web.manager.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2019/7/9
 * 16:07.
 */
@Component
public class WebApplicationRunner implements ApplicationRunner {
    @Autowired
    ScheduleService scheduleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scheduleService.startAllScheduleJobs();
    }
}
