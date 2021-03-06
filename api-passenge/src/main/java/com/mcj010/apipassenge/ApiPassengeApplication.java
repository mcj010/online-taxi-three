package com.mcj010.apipassenge;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ApiPassengeApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengeApplication.class, args);
    }

    //guava cache
    LoadingCache cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return "Load:" + new Random().nextInt(100);
        }
    });

    @PostMapping("/test-set/{id}")
    public String testSet(@PathVariable String id) {
        cache.put(id, "Set:" + new Random().nextInt(100));
        return "success";
    }

    @GetMapping("/test-get/{id}")
    public String testGet(@PathVariable String id) throws ExecutionException {
        // return cache.get(id).toString();
        return "成功";
    }


}
