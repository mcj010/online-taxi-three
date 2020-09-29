package com.mcj010.apipassenge.service;


import com.mcj010.internalcommon.dto.ResponseResult;

public interface VerificationCodeService {

    public ResponseResult send(String phoneNumber);

    public ResponseResult verify(String phoneNumber, String code);
}
