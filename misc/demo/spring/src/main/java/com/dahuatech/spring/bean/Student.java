package com.dahuatech.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: Student</p>
 * <p>date: 2023/3/19</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Value("jack")
    private String name;
    @Value("23")
    private int age;
}
