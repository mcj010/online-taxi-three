package com.mcj010.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.RequestContent;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lucy
 */
@Component
@Slf4j
public class RibbonFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String remoteAdrr =  request.getRequestURI();

        log.info("URI:{}",request.getRequestURI());// URI:/service-sms/test/sms-test31
        log.info("URL:{}",request.getRequestURL());// URL:http://localhost:9100/service-sms/test/sms-test31

        if(remoteAdrr.contains("/sms-test31")){
            ctx.set(FilterConstants.SERVICE_ID_KEY,"service-sms");
            ctx.set(FilterConstants.REQUEST_URI_KEY,"/test/sms-test3");
        }

        return null;
    }
}
