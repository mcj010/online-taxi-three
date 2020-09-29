package com.mcj010.serviceverificationcode.controller;

import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import com.mcj010.serviceverificationcode.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lucy
 */
@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {
    @Autowired
    VerifyCodeService verifyCodeService;

    /**
     * 根据身份，手机号，生成验证码
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable int identity, @PathVariable String phoneNumber) {
        log.info("/generate/{identity}/{phoneNumber} ： 身份类型：" + identity + ",手机号：" + phoneNumber);
        // 校验参数
        return verifyCodeService.generate(identity, phoneNumber);
    }


    @PostMapping("/verify")
    public ResponseResult verify(@RequestBody VerifyCodeRequest request) {
        log.info("/verify-code/verify  request:" + JSONObject.fromObject(request));
        //获取手机号和验证码
        String phoneNumber = request.getPhoneNumber();
        int identity = request.getIdentity();
        String code = request.getCode();

        return verifyCodeService.verify(identity, phoneNumber, code);
    }
}
