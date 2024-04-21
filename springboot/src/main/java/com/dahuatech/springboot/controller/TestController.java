package com.dahuatech.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahuatech.springboot.bean.Human;
import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TestController extends BaseController {
    @Autowired
    private Human human;

    @Autowired
    private Person person;

    @Autowired
    private Student student;

    @GetMapping("/human")
    public Human getHuman() {
        log.info("human request arrive...");
        // int result = 1 / 0;
        return human;
    }

    @GetMapping("/person")
    public Person getPerson() {
        log.info("person request arrive...");
        // int result = 1 / 0;
        return person;
    }

    @GetMapping("/student")
    public Student getStudent() {
        log.info("student request arrive...");
        return student;
    }

    @PostMapping("/upload")
    public Person uploadPerson(@RequestBody String json) {
        log.info("upload json {}", json);
        JSONObject jsonObj = JSON.parseObject(json);
        String name = jsonObj.getString("name");
        Integer age = jsonObj.getInteger("age");
        return new Person(name, age);
    }
}
