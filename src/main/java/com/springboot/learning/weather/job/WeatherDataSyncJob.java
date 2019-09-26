package com.springboot.learning.weather.job;

import com.springboot.learning.weather.service.impl.WeatherDataServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WeatherDataSyncJob extends QuartzJobBean {

    private static transient Log log = LogFactory.getLog(WeatherDataSyncJob.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Weather Data Sync Job");
    }
}
