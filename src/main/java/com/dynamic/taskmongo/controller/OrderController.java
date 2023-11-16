package com.dynamic.taskmongo.controller;

import com.dynamic.taskmongo.service.implementaion.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Alagu
 * Description: Controller for orders
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * Description: Get Request to fetch documents from collection
     * @param collectionName
     * @return List<Document>
     * @throws Exception
     */
    @GetMapping("/")
    public ResponseEntity<Object> getAll(@RequestParam(name = "collectionName",
            required = false, defaultValue = "orders") String collectionName) throws Exception {
        return new ResponseEntity<>(orderService.findAll(collectionName), HttpStatus.OK); //calling orderService to fetch list of documents from mentioned collection
    }
}
