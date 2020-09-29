package com.mcj010.apipassenge.service.impl;


import com.mcj010.apipassenge.service.ServicePassengerUserService;
import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.servicepassengeruser.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicePassengerUserServiceImpl implements ServicePassengerUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {
        String url = "http://service-passenger-user/auth/login";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassengerPhone(passengerPhone);
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<Object>(loginRequest,null),ResponseResult.class).getBody();

        return result;
    }

}
