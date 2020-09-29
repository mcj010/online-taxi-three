package com.mcj010.servicepassengeruser.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * service_passenger_user_info
 * @author 
 */
@Data
public class ServicePassengerUserInfo implements Serializable {
    private Long id;

    /**
     * 注册日期
     */
    private Date registerDate;

    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 性别。1：男，0：女
     */
    private Byte passengerGender;

    /**
     * 用户状态：1：有效，0：失效
     */
    private Byte userState;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}