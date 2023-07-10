package com.dahuatech.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: Config</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Configuration
@ComponentScan(basePackages = "com.dahuatech")
public class Config {
    @Bean
    public Student getStudent() {
        return new Student();
    }
}
