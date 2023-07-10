package com.dahuatech.springboot.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Person {
    // 优先从环境变量中拿属性值
    // 其次从配置文件(所有配置文件包含用@PropertySource注解加载的配置文件)中拿属性值
    // 最后拿不到则用后面的默认值
    @Value("${user.name:defaultName}")
    private String name;
    @Value("23")
    private int age;
}
