package com.dynamic.taskmongo.service;

import com.dynamic.taskmongo.dto.Product;
import com.dynamic.taskmongo.dto.Schedulers;
import com.dynamic.taskmongo.dto.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class DynamicScheduler implements SchedulingConfigurer {

    @Autowired
    private Schedulers scheduler;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<TaskConfig> tasks = scheduler.getTaskConfig();
        tasks.forEach(task -> {
            System.out.println(tasks);
            taskRegistrar.addTriggerTask(new Runnable() {
                @Override
                public void run() {
                    dynamicTask(task.getTask().getQuery());
                }
            }, new Trigger() {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
                    CronTrigger cronTrigger = new CronTrigger(task.getTask().getScheduleTime());
                    Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
                    return nextExecutionTime;
                }
            });
        });
    }

    public void dynamicTask(String query) {
        log.info("Current time： {} query: {}", LocalDateTime.now(),query);
        if (!mongoTemplate.collectionExists("orders")) {
            // The application is not connected to MongoDB
            System.out.println("yes");
        } else {
            // The application is connected to MongoDB
            System.out.println("no");
        }
//        Criteria criteria = new Criteria();
//        criteria.regex("orderedDay", "Sunday");
//        Query query1 = new Query(criteria);
//        List<Product> results  = mongoTemplate.find(query1, Product.class);
//        System.out.println(results);
//        Document query1 = Document.parse( query ) ;
//        MongoDatabase db = mongoTemplate.getDb();
//        db.runCommand(query1);
//        DBCollection collection = db.getCollection("mycollection");
//        BasicDBObject query1 = new BasicDBObject();
//        query1.put("mykey", "keyvalue");
//        DBCursor cursor = collection.find(query1);

    }
}
