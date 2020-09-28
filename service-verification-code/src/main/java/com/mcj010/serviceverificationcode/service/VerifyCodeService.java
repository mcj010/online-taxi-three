package com.mcj010.serviceverificationcode.service;

import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface VerifyCodeService {

    /**
     * 根据身份和手机号生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult<VerifyCodeResponse> generate(int identity , String phoneNumber);

    /**
     * 校验身份，手机号，验证码的合法性
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verify(int identity,String phoneNumber,String code);
}