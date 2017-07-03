package com.khirye.controller;

import com.khirye.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoukairui on 2017/6/30.
 */
@RestController
public class HelloController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;

    @Autowired
    private Person person;

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "hello Spring Boot!";
    }

    @GetMapping(value = "/error")
    public String sayError(){
        return "hi!something wrong!";
    }

    @GetMapping(value = "/getContent")
    public String getContent(){
        return name + age + content;
    }

    @GetMapping(value = "getPerson")
    public String getPerson(){
        return person.getName() + person.getAge();
    }

    @GetMapping(value = "getPathVar/{id}")
    public String getPathVar(@PathVariable String id){
        return id;
    }
}
