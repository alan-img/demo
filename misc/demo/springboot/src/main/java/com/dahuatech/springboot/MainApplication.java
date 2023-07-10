package com.dahuatech.springboot;

import com.dahuatech.springboot.bean.Human;
import com.dahuatech.springboot.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        Person person = context.getBean(Person.class);
        System.out.println(person);
        Human human = context.getBean(Human.class);
        System.out.println(human);
    }
}