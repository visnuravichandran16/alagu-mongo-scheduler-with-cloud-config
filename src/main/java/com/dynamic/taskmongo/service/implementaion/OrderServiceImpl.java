package com.dynamic.taskmongo.service.implementaion;

import com.dynamic.taskmongo.constant.CommonConstants;
import com.dynamic.taskmongo.exception.InvalidCollectionException;
import com.dynamic.taskmongo.service.interfaces.OrderService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Alagu
 * Description: Service layer for orders
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Description: this method will fetch the documents from collection
     * @param collectionName
     * @return List<Document>
     * @throws Exception
     */
    @Override
    public List<Document> findAll(String collectionName) throws Exception {
        if(!Arrays.stream(CommonConstants.AVAILABLE_COLLECTIONS.split(",")).toList().contains(collectionName)) { //checking for invalid collection name
            throw new InvalidCollectionException(CommonConstants.INVALID_COLLECTION);
        }
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName); //getting collection from db
        MongoCursor<Document> cursor = collection.find().iterator(); //creating a cursor later changed to list
        List<Document> documents = new ArrayList<>();
        cursor.forEachRemaining(documents::add);
        return documents;
    }
}
