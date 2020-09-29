package com.mcj010.apipassenge.service;


import com.mcj010.internalcommon.dto.ResponseResult;

public interface ServiceSmsRestTemplateService {

    public ResponseResult sendSms(String phoneNumber, String code);
}
