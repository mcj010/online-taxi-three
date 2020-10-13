package com.mcj010.cloudzuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 限流过滤器
 */
@Slf4j
@Component
public class LimitFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }
    // 2 qps(1秒  2个 请求 Query Per Second 每秒查询量)
    private static final  RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();

        if(RATE_LIMITER.tryAcquire()){
            log.info("通過");
            return null;
        }else{
            log.info("被限流了");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }

        return null;
    }
}
