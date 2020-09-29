package com.mcj010.apipassenge.service.impl;


import com.mcj010.apipassenge.service.ServiceVerificationCodeRestTemplateService;
import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceVerificationCodeRestTemplateServiceImpl implements ServiceVerificationCodeRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult generatorCode(int identity, String phoneNumber) {

        String url = "http://service-verification-code/verify-code/generate/" + identity + "/" + phoneNumber;

        // ResponseResult result = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<Object>(null,null),ResponseResult.class).getBody();
        ResponseResult result = restTemplate.getForObject(url, ResponseResult.class);
        return result;
    }

    @Override
    public ResponseResult verifyCode(int identity, String phoneNumber, String code) {

        String url = "http://service-verification-code/verify-code/verify/";

        VerifyCodeRequest request = new VerifyCodeRequest();
        request.setCode(code);
        request.setIdentity(identity);
        request.setPhoneNumber(phoneNumber);

        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(request, null), ResponseResult.class).getBody();

        return result;
    }
}
