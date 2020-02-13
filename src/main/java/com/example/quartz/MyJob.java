package com.example.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @ClassName MyJob
 * @Descriable 任务(也可以说是一个作业)
 **/
public class MyJob implements Job {
    //必须要重写execute方法
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 创建工作详情
        JobDetail jobDetail = context.getJobDetail();
        //获取工作的名称
        String name = jobDetail.getKey().getName(); //任务名
        String group = jobDetail.getKey().getGroup(); //任务group
        String job = jobDetail.getJobDataMap().getString("data"); // 任务中的数据
        System.out.println("job执行，job名:" +name+" ,group:" + group + " ,data:"+job + new Date());
    }
}
