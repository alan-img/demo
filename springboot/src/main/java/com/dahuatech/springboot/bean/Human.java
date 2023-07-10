package com.dahuatech.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "human")
// 加载指定的配置文件 否则加载application.properties
@PropertySource("classpath:human.properties")
public class Human {
    private String name;
    private int age;
}
