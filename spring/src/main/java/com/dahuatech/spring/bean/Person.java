package com.dahuatech.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: GPS</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
@Component
@PropertySource("classpath:person.properties")
public class Person {
    // @Autowired
    // public Environment env;
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;
}
