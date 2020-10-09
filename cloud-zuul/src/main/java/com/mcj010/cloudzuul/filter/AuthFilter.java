package com.mcj010.cloudzuul.filter;

import com.mcj010.internalcommon.constant.RedisKeyPrefixConstant;
import com.mcj010.internalcommon.util.JwtInfo;
import com.mcj010.internalcommon.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 该过滤器是否生效
     */
    @Override
    public boolean shouldFilter() {
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

//		String uri = request.getRequestURI();
//		System.out.println("来源uri："+uri);
//		//只有此接口/api-passenger/api-passenger-gateway-test/hello才被拦截
//		String checkUri = "/api-passenger/api-passenger-gateway-test/hello";
//		if(checkUri.equalsIgnoreCase(uri)) {
//			return true;
//		}
////		 测试路径
//		if(uri.contains("api-driver")) {
//			return true;
//		}

        return true;
    }

    /**
     * 拦截后的具体业务逻辑
     */
    @Override
    public Object run() throws ZuulException {

        System.out.println("auth 拦截");
        //获取上下文（重要，贯穿 所有filter，包含所有参数）
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

//        parseBody(request);

        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            JwtInfo tokenJwtInfo = JwtUtil.parseToken(token);

            if (null != tokenJwtInfo) {
                String tokenUserId = tokenJwtInfo.getSubject();
                Long tokenIssueDate = tokenJwtInfo.getIssueDate();

                BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + tokenUserId);
                String redisToken = stringStringBoundValueOperations.get();

                if (redisToken.equals(token)){
                    requestContext.setSendZuulResponse(true);
                    requestContext.setResponseStatusCode(HttpStatus.OK.value());
                    requestContext.set("isSuccess", true);

                    return null;
                }
            }
        }

        // 不往下走，还走剩下的过滤器，但是不向后面的服务转发。
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        requestContext.setResponseBody("auth fail");

//        requestContext.set("ifContinue",false);

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
     * 值越小，越在前
     */
    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 3;
    }


//	private void parseBody(HttpServletRequest request){
//        Map<String,Object> params = new HashMap<String, Object>();
//        BufferedReader br;
//        try {
//            br = request.getReader();
//            String str, wholeStr = "";
//            while((str = br.readLine()) != null){
//                wholeStr += str;
//            }
//            if(StringUtils.isNotEmpty(wholeStr)){
//                JSONObject jsonObject = JSONObject.fromObject(wholeStr);
//            }
//        } catch (IOException e1) {
//
//        }
//
//    }
}
