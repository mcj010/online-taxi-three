package com.mcj010.internalcommon.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class OrderIdUtils {

    public synchronized static String getOrderNumber(){

        StringBuffer sb = new StringBuffer();
        String time = DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS");
        String mcode = System.getProperty("mcode");
        String randomString = RandomStringUtils.randomNumeric(2);

        return sb.append(time).append(mcode).append(randomString).toString();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(getOrderNumber());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
