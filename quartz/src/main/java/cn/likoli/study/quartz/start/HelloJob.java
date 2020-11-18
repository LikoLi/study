package cn.likoli.study.quartz.start;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * HelloJob
 *
 * @author liko
 * @date 2020/11/2
 */
@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(this.getClass().getSimpleName() + "- run at: " + new Date());
    }
}
