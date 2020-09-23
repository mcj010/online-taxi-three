package com.mcj010.cloudeureka.listen;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.stereotype.Component;

@Component
public class TestEvent {
    public void listen(EurekaInstanceCanceledEvent event){

        //发邮件 短信
        System.out.println("下线"+event.getServerId());
    }
}
