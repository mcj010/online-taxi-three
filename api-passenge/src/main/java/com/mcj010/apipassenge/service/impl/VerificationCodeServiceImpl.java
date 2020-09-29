package com.mcj010.apipassenge.service.impl;


import com.mcj010.apipassenge.service.ServiceSmsRestTemplateService;
import com.mcj010.apipassenge.service.ServiceVerificationCodeRestTemplateService;
import com.mcj010.apipassenge.service.VerificationCodeService;
import com.mcj010.internalcommon.constant.CommonStatusEnum;
import com.mcj010.internalcommon.constant.IdentityConstant;
import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServiceSmsRestTemplateService serviceSmsRestTemplateService;

    @Override
    public ResponseResult send(String phoneNumber) {

        // 获取验证码
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.generatorCode(IdentityConstant.PASSENGER, phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (responseResult.getCode() == CommonStatusEnum.SUCCESS.getCode()) {
            JSONObject data = JSONObject.fromObject(responseResult.getData().toString());
            verifyCodeResponse = (VerifyCodeResponse) JSONObject.toBean(data, VerifyCodeResponse.class);

        } else {
            return ResponseResult.fail("获取验证码失败");
        }

//        String code = verifyCodeResponse.getCode();
//
//        ResponseResult result = serviceSmsRestTemplateService.sendSms(phoneNumber,code);
//        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()){
//            return ResponseResult.fail("发送短信 失败");
//        }

        //return ResponseResult.success("");
        return responseResult;
    }

    @Override
    public ResponseResult verify(String phoneNumber, String code) {

        return serviceVerificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER, phoneNumber, code);
    }

}
