package com.mcj010.servicesms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lucy
 */
@RestController
@RequestMapping("/test")
public class ServiceSmsTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/sms-test")
    public String test(){

        return "sms-test:"+port;
    }

    @GetMapping("/sms-test2")
    public String test2(){

        return "sms-test2:"+port;
    }
    @GetMapping("/sms-test3")
    public String test3(){

        return "sms-test3:"+port;
    }


    @GetMapping("/sms-test4")
    public String test4(HttpServletRequest request){

        String token = request.getHeader("token");

        return "sms-test3:"+port+":"+token;
    }

    @GetMapping("/sms-test5")
    public String test5(HttpServletRequest request){

        String token = request.getHeader("Cookie");

        return "sms-test3:"+port+":"+token;
    }
}
