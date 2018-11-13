package com.pzubaha.jsop;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StrarterParser {
    public static void main(String[] args) throws SchedulerException, IOException {
        Properties props = new Properties();
        try (InputStream in = StrarterParser.class.getResourceAsStream("/app.properties")) {
            props.load(in);
        }
        JobKey jobKeyA = new JobKey("job", "g1");
        JobDetail job = JobBuilder.newJob(Parser.class)
                .withIdentity(jobKeyA).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("triggerName", "g1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(props.getProperty("cron.time"))) // you can set here your comfortable job time...
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
