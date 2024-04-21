package com.dahuatech.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        System.out.println("start...");
        try {
            throw new OutOfMemoryError("alan");
        } catch (OutOfMemoryError err) {
            logger.error("happen error", err);
        }
        System.out.println("end...");
    }
}

