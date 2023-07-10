package com.dahuatech.springboot.controller;

import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
public class PersonController extends BaseController {
    @Autowired
    private Person person;

    private ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();

    @GetMapping("/person")
    public Person person() {
        log.info("accept request...");
        if (person != null) {
            throw new RequestException("request exception", 201);
        }
        return person;
    }

    @RequestMapping("/upload")
    public Person upload(@RequestBody String jsonString) {
        log.info(jsonString);
        String[] lines = jsonString.split(System.lineSeparator());
        for (String line : lines) {
            String[] fields = line.split(",");
            hashMap.put(fields[0], fields[1]);
        }

        return person;
    }

    @GetMapping("/hashMap")
    public ConcurrentHashMap<String, Object> hashMap() {
        return hashMap;
    }
}
