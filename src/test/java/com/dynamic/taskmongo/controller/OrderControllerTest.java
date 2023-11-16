package com.dynamic.taskmongo.controller;

import com.dynamic.taskmongo.service.implementaion.OrderServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Test
    public void getAllTest() throws Exception {
        String collectionName = "";
        orderController.getAll(collectionName);
    }
}
