package com.example.demo.quartz;

import org.quartz.*;
import java.util.Date;

/**
 * @Author zhoushenghua on
 */
@DisallowConcurrentExecution
public class HelloQuartz implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        String score = detail.getJobDataMap().getString("score");
        System.out.println("name is " + name + ",score is " + score + " at " + new Date());
    }
}
