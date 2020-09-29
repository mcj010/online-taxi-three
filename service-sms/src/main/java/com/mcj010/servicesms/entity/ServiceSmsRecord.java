package com.mcj010.servicesms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * service_sms_record
 * @author 
 */
@Data
public class ServiceSmsRecord implements Serializable {
    private Integer id;

    /**
     * 乘客手机号
     */
    private String phoneNumber;

    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 发送状态 0:失败  1: 成功
     */
    private Integer sendFlag;

    /**
     * 发送失败次数
     */
    private Integer sendNumber;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}