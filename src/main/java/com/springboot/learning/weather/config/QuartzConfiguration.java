package com.springboot.learning.weather.config;

import com.springboot.learning.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail weatherDataSyncJobJobDetial(){

        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("WeatherDataSyncJob")
                .storeDurably().build();

    }

//    @Bean
////    public Trigger weatherDataSyncJobTrigger(){
////        SimpleScheduleBuilder schedBuild = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
////        return TriggerBuilder.newTrigger()
////                .forJob(weatherDataSyncJobJobDetial())
////                .forJob("weatherDataSyncJobTrigger")
////                .withSchedule(schedBuild).build();
////
////    }

}
