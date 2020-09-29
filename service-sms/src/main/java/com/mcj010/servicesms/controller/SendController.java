package com.mcj010.servicesms.controller;


import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.servicesms.request.SmsSendRequest;
import com.mcj010.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/send")
@Slf4j
public class SendController {
	
	@Autowired
	private SmsService smsService;

	@RequestMapping(value = "/sms-template",method = RequestMethod.POST)
    public ResponseResult send(@RequestBody SmsSendRequest smsSendRequest){
		//输出收到的参数内容
        JSONObject param = JSONObject.fromObject(smsSendRequest);
        log.info("/send/alisms-template   request："+param.toString());
        return smsService.sendSms(smsSendRequest);
    }
	
}