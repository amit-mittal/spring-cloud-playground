package com.playground.configclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${lucky-word}")
    String luckyWord;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLuckyWord() {
        return "The lucky word is " + luckyWord;
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String doFailedCall() {
        throw new RuntimeException("Fail!!!!");
    }

    public String fallbackMethod() {
        return "Don't worry, it's been taken care";
    }
}
