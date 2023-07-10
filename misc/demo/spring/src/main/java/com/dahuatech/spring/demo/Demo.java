package com.dahuatech.spring.demo;

import com.dahuatech.spring.bean.Config;
import com.dahuatech.spring.controller.PersonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Demo {
    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info(logger.getClass().toString());

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean("person"));
        PersonController p = (PersonController) context.getBean("personController");
        p.getPerson(1234);
        PersonController p1 = context.getBean(PersonController.class);
        p1.getPerson(1001);
        System.out.println();
        System.out.println(context.getBeanDefinitionNames().length);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

    }
}
