package com.dynamic.taskmongo.service;

import com.dynamic.taskmongo.service.implementaion.OrderServiceImpl;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    MongoTemplate mongoTemplate;
    @Mock
    MongoCollection mongoCollection;

    @Test
    public void findAllTest() throws Exception {
        String collectionName = "customers";
        orderService.findAll(collectionName);
    }

    @Test
    public void findAll() throws Exception {
        String collectionName = "customers";
        Document customer = new Document();
        customer.put("name", "John Doe");
        customer.put("age", 30);
        mongoCollection = (MongoCollection) mongoTemplate.insert(customer, "customers");
        when(mongoTemplate.getCollection(anyString())).thenReturn(mongoCollection);
        orderService.findAll(collectionName);
    }
}
