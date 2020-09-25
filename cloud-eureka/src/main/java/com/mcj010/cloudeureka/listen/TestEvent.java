package com.mcj010.cloudeureka.listen;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class TestEvent {
    public void listen(EurekaInstanceCanceledEvent event){

        //发邮件 短信
        System.out.println("下线"+event.getServerId());
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2,
//                new ThreadFactoryBuilder()
//                        .setNameFormat("DiscoveryClient-%d")
//                        .setDaemon(true)
//                        .build());
    }
}
