package com.dynamic.taskmongo.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: Alagu
 * Description: This dto is created to map config
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "schedulers")
public class Schedulers {

    private List<TaskConfig> taskConfig;
}
