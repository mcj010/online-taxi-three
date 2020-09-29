package com.mcj010.servicesms.dao;

import com.mcj010.servicesms.entity.ServiceSmsTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceSmsTemplateCustomDao extends ServiceSmsTemplateDao {

    ServiceSmsTemplate selectByTemplateId(String templateId);
}
