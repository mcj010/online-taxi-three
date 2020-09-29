package com.mcj010.servicesms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * service_sms_template
 * @author 
 */
@Data
public class ServiceSmsTemplate implements Serializable {
    private Integer id;

    /**
     * 短信模板ID
     */
    private String templateId;

    private String templateName;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 模板类型（1：营销；2：通知；3：订单）
     */
    private Boolean templateType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}