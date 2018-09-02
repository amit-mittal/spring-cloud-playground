package com.playground.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class MainClient {

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLuckyWord() {
        return "The lucky word is coming from eureka-client";
    }

    @RequestMapping(value = "/config-client", method = RequestMethod.GET)
    public String getLuckyWordFromConfigClient() {
        List<ServiceInstance> list = discoveryClient.getInstances("sample-config-client");
        String response = "null";
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri != null ) {
                response = (new RestTemplate()).getForObject(uri, String.class);
            }
        }

        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainClient.class, args);
    }
}
