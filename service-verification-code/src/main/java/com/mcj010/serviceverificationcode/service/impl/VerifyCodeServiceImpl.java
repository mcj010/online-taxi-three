package com.mcj010.serviceverificationcode.service.impl;

import com.mcj010.internalcommon.constant.CommonStatusEnum;
import com.mcj010.internalcommon.constant.IdentityConstant;
import com.mcj010.internalcommon.constant.RedisKeyPrefixConstant;
import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.mcj010.serviceverificationcode.service.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 生成验证码
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {

        //校验 发送时限，三挡验证，不能无限制发短信
//        checkSendCodeTimeLimit(phoneNumber);

        // 0.9*9=8.1+1 9,去掉首位为0的情况。 0.11225478552211(0.0-<1)
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));

        /**
         * 有人用这种写法。生成6位code，错误用法，虽然大部分情况下结果正确，但不能这么用，偶尔位数不够？
         */
//        String code = String.valueOf(new Random().nextInt(1000000));

        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber; //passenger_login_code_  + 135555555555
        //存redis，2分钟过期
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);

//        Boolean aBoolean = codeRedis.setIfAbsent(code);
//        if (aBoolean){
//            codeRedis.expire(2,TimeUnit.MINUTES);
//        }
        codeRedis.set(code, 30, TimeUnit.MINUTES);
//        codeRedis.expire(2,TimeUnit.MINUTES);

        //返回
        VerifyCodeResponse result = new VerifyCodeResponse();
        result.setCode(code);
        return ResponseResult.success(result);
    }


    @Override
    public ResponseResult verify(int identity, String phoneNumber, String code) {
        //三档验证

        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber; ////passenger_login_code_  + 135555555555
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();

        if (StringUtils.isNotBlank(code)
                && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {

            return ResponseResult.success("");
        } else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }

    }


    /**
     * 判断此手机号发送时限限制
     *
     * @param phoneNumber
     * @return
     */
    private ResponseResult checkSendCodeTimeLimit(String phoneNumber) {
        //判断是否有 限制1分钟，10分钟，24小时。

        return ResponseResult.success("");
    }

    /**
     * 根据身份类型生成对应的缓存key
     *
     * @param identity
     * @return
     */
    private String generateKeyPreByIdentity(int identity) {
        String keyPre = "";
        //1
        if (identity == IdentityConstant.PASSENGER) {
            keyPre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
            //2
        } else if (identity == IdentityConstant.DRIVER) {
            keyPre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return keyPre;
    }

    /**
     * 三档验证校验
     *
     * @param phoneNumber
     * @param code
     * @return
     */
    private ResponseResult checkCodeThreeLimit(String phoneNumber, String code) {
        //看流程图

        return ResponseResult.success("");
    }

    public static void main(String[] args) {

//        int count = 0;
//        for (int i = 0; i < 5000; i++) {
////            String code = String.valueOf(new Random().nextInt(1000000));
//            String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
//            if (code.length()<6){
//                System.out.println("有小于6位长的数"+i);
//                count++;
//            }
//        }
//        System.out.println(count);

// ----------------------------------------------------------------------
////        System.out.println(Math.random());
//        int sum = 10000000;
////        int sum = Integer.MAX_VALUE;
//        System.out.println(sum);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < sum; i++) {
//            String a = (Math.random() + "").substring(2,8);
//        }
//        System.out.println("字符串截取时间："+(System.currentTimeMillis() - start));
//
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < sum; i++) {
//            String a = String.valueOf((int)((Math.random()*9)*Math.pow(10,5)));
//        }
//        System.out.println("字符串求乘方："+(System.currentTimeMillis() - start1));


    }
}
