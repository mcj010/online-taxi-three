package com.mcj010.internalcommon.dto.servicesms;

import lombok.Data;

import java.util.Map;

/**
 * @author yueyi2019
 */
@Data
public class SmsTemplateDto {
	// 模板id
	private String id;

	// 参数  占位符
	private Map<String, Object> templateMap;

	@Override
	public String toString() {
		return "SmsTemplateDto [id=" + id + ", templateMap=" + templateMap + "]";
	}

}