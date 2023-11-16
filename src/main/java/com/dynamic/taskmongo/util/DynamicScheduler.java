package com.dynamic.taskmongo.util;

import com.dynamic.taskmongo.constant.CommonConstants;
import com.dynamic.taskmongo.dto.Schedulers;
import com.dynamic.taskmongo.dto.Task;
import com.dynamic.taskmongo.dto.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.List;

/**
 * @Author: Alagu
 * Description: class for dynamic scheduling
 */
@Slf4j
@Configuration
@EnableScheduling
public class DynamicScheduler implements SchedulingConfigurer {

    @Autowired
    private Schedulers scheduler;
    @Autowired
    private Aggregation aggregation;
    @Autowired
    private Updation updation;

    /**
     * Description: this method creates tasks and allocates time
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<TaskConfig> tasks = scheduler.getTaskConfig(); //getting values from config file
        tasks.forEach(task -> taskRegistrar.addTriggerTask(() -> dynamicTask(task.getTask()), //looping through each config and creating task
                triggerContext -> createCron(triggerContext, task.getTask().getScheduleTime())));
    }

    /**
     * Description: this method is used to create dynamic mongo query execution task
     * @param task
     */
    public void dynamicTask(Task task) {
        try {
            updation.insert(aggregation.createAggregation(task), task.getOutputCollection()); // getting the result from aggregation and inserting into new collection
        } catch (Exception e) {
            log.error(CommonConstants.UNSUPPORTED_QUERRY,task.getQuery()); // handling unsupported query
        }
    }

    /**
     * Description: this method is used to create cron trigger for dynamic task
     * @param triggerContext
     * @param scheduleTime
     * @return
     */
    public Date createCron(TriggerContext triggerContext, String scheduleTime) {
        CronTrigger cronTrigger = new CronTrigger(scheduleTime); // creating next execution time for givem cron trigger
        return cronTrigger.nextExecutionTime(triggerContext);
    }
}
