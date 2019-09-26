package com.springboot.learning.weather.schedual;


import com.springboot.learning.timingjob.task.StudyTask;
import com.springboot.learning.weather.service.impl.WeatherDataServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@EnableScheduling
@Component
public class SaticScheduleTask {

    private static transient Log log = LogFactory.getLog(SaticScheduleTask.class);

    @Scheduled(cron = "0 44 9 * * ?")
    public void clock(){

        log.error("This is a job , I am doing ...");

        StudyTask task = new StudyTask();

        task.task();

    }


    @Scheduled(fixedRate = 5000)
    public void timerToZZP(){
        System.out.println("ZZP:" + new
                Random().nextLong() + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    @Scheduled(fixedDelay = 50000)
    public void timerToReportCount(){
        for (int i = 0; i < 10; i++){
            System.out.println("<================its" + i + "count===============>" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    @Scheduled(initialDelay = 50000,fixedRate = 6000)
    public void timerToReport(){
        for (int i = 0; i < 10; i++){
            System.out.println("<================delay :" + i + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "count===============>");
        }
    }

}
