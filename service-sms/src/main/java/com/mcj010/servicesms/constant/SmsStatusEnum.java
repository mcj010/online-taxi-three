package com.mcj010.servicesms.constant;

import lombok.Getter;

/**
 *  第三方短信 错误码
 * @author yueyi2019
 */
public enum SmsStatusEnum {
	
	/**
     * 操作成功
     */
    SEND_SUCCESS(0, "sms send success"),
    
    /**
     * 操作异常
     */
    INTERNAL_SERVER_EXCEPTION(-1, "exception"),
	
    /**
     * 操作失败
     */
    SEND_FAIL(1, "sms send fail");
	
	@Getter
	private final int code;
	
	@Getter
    private final String value;
    
    private SmsStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}