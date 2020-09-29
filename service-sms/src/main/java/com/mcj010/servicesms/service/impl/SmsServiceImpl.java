package com.mcj010.servicesms.service.impl;


import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.servicesms.SmsTemplateDto;
import com.mcj010.internalcommon.dto.servicesms.request.SmsSendRequest;
import com.mcj010.servicesms.constant.SmsStatusEnum;
import com.mcj010.servicesms.dao.ServiceSmsRecordDao;
import com.mcj010.servicesms.dao.ServiceSmsTemplateCustomDao;
import com.mcj010.servicesms.entity.ServiceSmsRecord;
import com.mcj010.servicesms.entity.ServiceSmsTemplate;
import com.mcj010.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    /**
     *   缓存用于替换内容的模板
     */
    private Map<String, String> templateMaps = new HashMap<String, String>();

    @Autowired
    private ServiceSmsTemplateCustomDao serviceSmsTemplateCustomDao;

    @Autowired
    private ServiceSmsRecordDao serviceSmsRecordDao;

    @Override
    public ResponseResult sendSms(SmsSendRequest request) {
        log.info(request.toString());

        for (String phoneNumber : request.getReceivers()) {
            List<SmsTemplateDto> templates = request.getData();

            ServiceSmsRecord sms = new ServiceSmsRecord();
            sms.setPhoneNumber(phoneNumber);
            // 81B*10 1024 1K 10*1024  1M
            for (SmsTemplateDto template : templates) {
                // 从DB加载模板内容至缓存
                if (!templateMaps.containsKey(template.getId())) {
                    //此处注释掉的内容为，将db模板加载到内存
                    ServiceSmsTemplate t = serviceSmsTemplateCustomDao.selectByTemplateId(template.getId());
                    System.out.println(t.getTemplateContent());
                    templateMaps.put(template.getId(),
                            serviceSmsTemplateCustomDao.selectByTemplateId(template.getId()).getTemplateContent());
                }

                // 替换占位符
                String content = templateMaps.get(template.getId());
                for (Map.Entry<String, Object> entry : template.getTemplateMap().entrySet()) {
                    content = StringUtils.replace(content, "${" + entry.getKey() + "}", "" + entry.getValue());
                }

                // 发生错误时，不影响其他手机号和模板的发送
                try {
                    int result = send(phoneNumber, template.getId(), template.getTemplateMap());

                    // 组装SMS对象
                    sms.setSendTime(new Date());
                    sms.setOperatorName("");
                    sms.setSendFlag(1);
                    sms.setSendNumber(0);
                    sms.setSmsContent(content);

                    if (result != SmsStatusEnum.SEND_SUCCESS.getCode()) {
                        throw new Exception("短信发送失败");
                    }
                } catch (Exception e) {
                    sms.setSendFlag(0);
                    sms.setSendNumber(1);
                    log.error("发送短信（" + template.getId() + "）失败：" + phoneNumber, e);
                } finally {
                    sms.setCreateTime(new Date());
                    serviceSmsRecordDao.insert(sms);
                }
            }
        }
        return ResponseResult.success("");
    }

    private int send(String phoneNumber, String templateId, Map<String, ?> map) throws Exception {
        JSONObject param = new JSONObject();
        param.putAll(map);

        return sendMsg(phoneNumber, templateId, param.toString());
    }

    private int sendMsg(String phoneNumber, String templateCode, String param) {

        /**
         *  供应商 发 短信
         */
        return SmsStatusEnum.SEND_SUCCESS.getCode();

    }
}
