package com.dahuatech.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahuatech.springboot.bean.GPS;
import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.bean.ResponseBean;
import com.dahuatech.springboot.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
public class PersonController extends BaseController {
    @Autowired
    private Person person;

    private ConcurrentHashMap<String, GPS> hashMap = new ConcurrentHashMap<>();

    @GetMapping("/person")
    public Person person() {
        log.info("person request arrive...");
        int result = 1 / 0;
        return person;
    }

    @PostMapping("/time/space/file")
    public ResponseBean upload(@RequestBody String jsonString) {
        // 1.打印日志
        log.info("receive: " + jsonString);

        // 模拟异常
        // int a = 1 / 0;

        // 2.字符串校验
        if (StringUtils.isBlank(jsonString)) {
            throw new GlobalException(500, "receive time space file is null or '' or ' '");
        }

        // 3.清空老时空域数据
        hashMap.clear();

        // 4.覆盖时空域信息
        List<JSONObject> list = JSON.parseArray(jsonString, JSONObject.class);
        for (JSONObject obj : list) {
            String channel = obj.getString("channel");
            Double gpX = obj.getDouble("gpX");
            Double gpY = obj.getDouble("gpY");
            hashMap.put(channel, new GPS(gpX, gpY));
        }

        return new ResponseBean(200, "normal");
    }

    @GetMapping("/hashMap")
    public ConcurrentHashMap<String, GPS> hashMap() {
        log.info(hashMap.toString());
        return hashMap;
    }
}
