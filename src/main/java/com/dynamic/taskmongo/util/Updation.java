package com.dynamic.taskmongo.util;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

/**
 * @Author: Alagu
 * Description: class for update function
 */
@Component
public class Updation {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Description: this method is used to insert value to view collection
     * @param results
     * @param outputCollection
     */
    public void insert(AggregationResults<Document> results, String outputCollection) {
        mongoTemplate.dropCollection(outputCollection); //dropping the existing collection inorder to create new collection to prevert duplication
        mongoTemplate.createCollection(outputCollection);
        results.getMappedResults().forEach(result -> mongoTemplate.insert(result, outputCollection)); // inserting each result into given collection
    }
}
