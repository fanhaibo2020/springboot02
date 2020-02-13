package com.example.quartz;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName QuartzTest
 * @Date 2020/2/12 12:09
 **/
public class QuartzTest {
    public static void main(String[] args) {
        simpleQuartz();
        testCronTrigger();
    }

    public static void simpleQuartz(){
        try{
            //创建scheduler,调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //定义一个Trigger,触发条件类
//            TriggerBuilder triggerBuilder = ;
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义name/group
                    .startNow()  // 一旦加入scheduler,立即生效，即开始时间
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(3) //每隔3秒执行一次
                            .repeatForever()) // 一直执行，直到结束时间
                    .build();
//                    .endAt(new GregorianCalendar(2020,2,13,16,7,0).getTime());
            //定义一个JobDetail
            //定义Job类为MyJob类，这是真正的执行逻辑所在
            JobDetail job = JobBuilder.newJob(MyJob.class)
                    .withIdentity("测试任务1","test")  //定义name/group
                    .usingJobData("data","22")  //定义属性，存储数据
                    .build();
            //调度器中加入任务和触发器
            scheduler.scheduleJob(job,trigger);
            //启动任务调度
            scheduler.start();
            //关闭调度器
            Thread.sleep(100000);  //在主线程休眠的情况下,任务调度器仍然会执行，应为它是异步的
            scheduler.shutdown();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void testCronTrigger(){
        try{
            //创建scheduler,调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //定义一个Trigger,触发条件类
            Trigger trigger = newTrigger().
                    withIdentity("trigger1", "group1") //定义name/group
                    .startNow()  // 一旦加入scheduler,立即生效，即开始时间
                    .withSchedule(cronSchedule("0/3 * * * * ?")) // 一直执行，直到结束时间
                    .build();
            //定义一个JobDetail
            //定义Job类为MyJob类，这是真正的执行逻辑所在
            JobDetail job = JobBuilder.newJob(MyJob2.class)
                    .withIdentity("cron","cronTrigger")  //定义name/group
                    .build();
            //调度器中加入任务和触发器
            scheduler.scheduleJob(job,trigger);
            //启动任务调度
            scheduler.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
