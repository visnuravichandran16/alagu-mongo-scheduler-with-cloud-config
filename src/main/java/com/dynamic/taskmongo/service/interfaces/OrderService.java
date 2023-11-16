package com.dynamic.taskmongo.service.interfaces;

import org.bson.Document;

import java.util.List;

/**
 * @Author: Alagu
 * Description: interface for order service
 */
public interface OrderService {

    /**
     *
     * @param collectionName
     * @return
     * @throws Exception
     */
    List<Document> findAll(String collectionName) throws Exception;
}
