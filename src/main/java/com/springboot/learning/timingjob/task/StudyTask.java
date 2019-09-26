package com.springboot.learning.timingjob.task;

import com.springboot.learning.timingjob.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class StudyTask {

    public void task() {
        // 该 map 可在 job 中获取
        JobDataMap map = new JobDataMap();
        map.put("hello", "world");

        JobDetail jobDetail = newJob(QuartzJob.class).
                withIdentity("myJob", "myGroup").
                setJobData(map).build();
        /*
         * 简单定时器
         *
         * 执行时间间隔
         * withIntervalInMilliSeconds 毫秒
         * withIntervalInSeconds 秒
         * withIntervalInMinutes 分钟
         * withIntervalInHours 小时
         *
         * 执行次数
         * repeatForever 重复执行
         * withRepeatCount 次数
         */
        SimpleScheduleBuilder scheduleBuilder = simpleSchedule().withIntervalInSeconds(3).withRepeatCount(10);

        /*
         * corn定时器
         *
         * corn表达式，使用更灵活
         * corn表达式在线生成 http://cron.qqe2.com/
         */
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */2 * * * ?");

        Trigger trigger = newTrigger().startAt(new Date()).//startNow() 默认现在开始
                withIdentity("myTrigger", "myGroup").
                //withSchedule(scheduleBuilder).build();
                        withSchedule(cronScheduleBuilder).build();

        try {
            //1.创建Scheduler工厂
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            //2.获取实例
            Scheduler scheduler = schedulerFactory.getScheduler();
            //3.设置jobDetail详情和trigger触发器
            scheduler.scheduleJob(jobDetail, trigger);
            //4.定时任务开始
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


}
