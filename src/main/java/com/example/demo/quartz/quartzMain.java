package com.example.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhoushenghua on
 */
public class quartzMain {

    public static void main(String[] args) {
        //传递参数用
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "zsh");
        paramMap.put("score", "100");

        //定义一个JobDetail
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class)
                //定义name和group
                .withIdentity("job_zsh", "group_zsh")
                //定义需要传递的内容
                .usingJobData("name", paramMap.get("name"))
                .usingJobData("score", paramMap.get("score"))
                .build();

        //定义一个Trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_zsh", "group_zsh")
                //加入scheduler之后立刻执行
                .startNow()
                //定时,每隔一秒执行一次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        try{
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                //e.printStackTrace();
            }
            scheduler.shutdown();
        }catch (SchedulerException e){
            //e.printStackTrace();
        }
    }

}
