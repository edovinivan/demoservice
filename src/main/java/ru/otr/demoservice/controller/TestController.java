package ru.otr.demoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hhh")
    public String getHhh(){
        return "OK! Demo service response " + new Date();
    }
}
