package com.dynamic.taskmongo.dto;

import lombok.Data;

/**
 * @Author: Alagu
 * Description: This dto is created to map config
 */
@Data
public class Task {

    private String query;
    private String scheduleTime;
    private String outputCollection;
    private String description;
}
