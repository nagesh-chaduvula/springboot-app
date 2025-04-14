package com.springboot.controller;

import com.springboot.HelloworldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloworldController {
    private final HelloworldService helloworldService;

    @RequestMapping("/hello")
    public String hello() {
        String helloMsg = helloworldService.hello();
        return helloMsg;
    }

    @RequestMapping("/greet")
    public String greet() {
        String greetMsg = helloworldService.greet();
        return greetMsg;
    }
}
