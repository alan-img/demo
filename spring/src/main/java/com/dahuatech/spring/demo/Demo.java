package com.dahuatech.spring.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahuatech.spring.bean.Config;
import com.dahuatech.spring.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Demo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);
        System.out.println(JSON.toJSONString(person));

        JSONObject obj = new JSONObject();
        obj.put("name", "alna");
        System.out.println(JSON.toJSONString(obj));
    }
}
