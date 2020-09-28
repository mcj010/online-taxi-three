package com.mcj010.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 鉴权filter
 * @author yueyi2019
 */
@Component
public class AuthFilter2 extends ZuulFilter {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 	该过滤器是否生效
	 */
	@Override
	public boolean shouldFilter() {
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		if (!requestContext.sendZuulResponse()){
			return false;
		}

//		boolean ifContinue = (boolean) requestContext.get("ifContinue");
//		if (ifContinue){
//			return true;
//		}else {
//			return false;
//		}

		return true;
	}
	
	/**
	 * 	拦截后的具体业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("auth2 拦截");
		return null;
	}
	/**
	 * 拦截类型，4中类型。
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * 	值越小，越在前
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 10;
	}

}