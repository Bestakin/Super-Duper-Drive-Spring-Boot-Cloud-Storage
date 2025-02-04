package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private Map<String, Object> map = new HashMap<>();

    @RequestMapping("/hello")
    public Map<String, Object> Hello(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello, World!");
        response.put("status", "success");
        response.put("timestamp", System.currentTimeMillis());
        return response; // Return the response as JSON
    }
}
