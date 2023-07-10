package com.dahuatech.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    private static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

        // System.out.println();
        // System.out.println(logger.getClass());
        // System.out.println();
        //
        // System.out.println(context.getBeanDefinitionNames().length);
        // for (String name : context.getBeanDefinitionNames()) {
        //     System.out.println(name);
        // }
        //
        // System.out.println();
        // Human human = context.getBean(Human.class);
        // System.out.println(human);
    }
}
