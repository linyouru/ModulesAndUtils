package com.lyr.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName PrintWordsJob
 * @Description TODO
 * @Author LYR
 * @Date 2019/8/12 17:12
 * @Version 1.0
 **/
public class PrintWordsJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String printTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("当前时间："+printTime+"");
    }
}
