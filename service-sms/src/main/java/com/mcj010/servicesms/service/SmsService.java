package com.mcj010.servicesms.service;


import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.servicesms.request.SmsSendRequest;

public interface SmsService {
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	public ResponseResult sendSms(SmsSendRequest request);
}