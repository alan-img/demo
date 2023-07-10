package com.dahuatech.springboot.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Person {
    @Value("alan")
    private String name;
    @Value("23")
    private int age;
}
