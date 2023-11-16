package com.dynamic.taskmongo.util;

import com.dynamic.taskmongo.constant.CommonConstants;
import com.dynamic.taskmongo.dto.Task;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

/**
 * @Author: Alagu
 * Description: Class containing methods of aggregation
 */
@Component
public class Aggregation {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Description: this method is used to create stages of aggregation
     * @param task
     * @return AggregationResults<Document>
     */
    public AggregationResults<Document> createAggregation(Task task) {
        AggregationOperation customAggregationOperation = context -> Document.parse(task.getQuery()); //creating aggregation with the given query

        org.springframework.data.mongodb.core.aggregation.Aggregation aggregation = // extracting the custom aggregation
                org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation(
                customAggregationOperation
        );

        return mongoTemplate.aggregate(aggregation, CommonConstants.PRIMARY_COLLECTION, Document.class); //executing the aggregation
    }
}
