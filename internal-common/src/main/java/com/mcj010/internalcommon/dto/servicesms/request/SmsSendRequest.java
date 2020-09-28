package com.mcj010.internalcommon.dto.servicesms.request;


import com.mcj010.internalcommon.dto.servicesms.SmsTemplateDto;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi2019
 */
@Data
public class SmsSendRequest {

	private String[] receivers;
	private List<SmsTemplateDto> data;

	@Override
	public String toString() {
		return "SmsSendRequest [reveivers=" + Arrays.toString(receivers) + ", data=" + data + "]";
	}

}