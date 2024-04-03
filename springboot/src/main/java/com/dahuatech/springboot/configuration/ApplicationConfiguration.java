package com.dahuatech.springboot.configuration;

import com.dahuatech.springboot.bean.Human;
import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.configuration</p>
 * <p>className: ApplicationConfiguration</p>
 * <p>date: 2023/3/19</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Configuration
@Import({Human.class}) // 使用Import自动向IOC容器中注入对应类型的对象
public class ApplicationConfiguration {
    @Bean
    public Student getStudent(Person person) { // 方法参数Person对象会从IOC容器找查找并自动注入
        return new Student("jack", 23);
    }
}
