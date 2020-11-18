package cn.likoli.study.quartz.start;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * QuartzStart
 *
 * @author liko
 * @date 2020/11/2
 */
@Component
public class QuartzStart {

    @PostConstruct
    public void postConstruct() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

        scheduler.shutdown();
    }


    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

        JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

        scheduler.scheduleJob(job, trigger);

//        scheduler.shutdown();
    }
}
