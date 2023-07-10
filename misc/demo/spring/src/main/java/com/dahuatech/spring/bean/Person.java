package com.dahuatech.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: Person</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Person {
    @Value("alan")
    private String name;
    @Value("28")
    private int age;

    @PostConstruct
    public void init() {
        System.out.println("constructor");
    }
}
