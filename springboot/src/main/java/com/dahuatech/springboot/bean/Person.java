package com.dahuatech.springboot.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Person {
    @Value("${NAME:alan}") // 默认有限读取环境变量，如果环境变量不存在则读取默认值
    private String name;
    @Value("23")
    private int age;
}
