package com.dahuatech.springboot.controller;

import com.dahuatech.springboot.bean.Human;
import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Person person() {
        log.info("person request arrive...");
        // int result = 1 / 0;
        return person;
    }

    @GetMapping("/student")
    public Student getStudent() {
        log.info("student request arrive...");
        return student;
    }
}
