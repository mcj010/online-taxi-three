package com.mcj010.apipassenge.service;

import com.mcj010.internalcommon.dto.ResponseResult;

/**
 * 验证码生成
 */
public interface ServiceVerificationCodeRestTemplateService {

    /**
     * 生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult generatorCode(int identity, String phoneNumber);

    /**
     * 验证验证码
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verifyCode(int identity, String phoneNumber, String code);
}
