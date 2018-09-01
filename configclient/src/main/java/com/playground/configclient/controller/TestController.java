package com.playground.configclient.controller;

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
}
