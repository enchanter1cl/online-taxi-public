package com.erato.apidriver.interceptor;

import com.erato.internalcommon.constant.TokenConstant;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.dto.TokenResult;
import com.erato.internalcommon.util.JwtUtils;
import com.erato.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public class JwtInterceptor implements HandlerInterceptor {
    
    /* 正常情况下，when initialize interceptors, BEANs 都还没开始 initialize */
    @Autowired
    StringRedisTemplate strRedisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean resFlag = true;
        String resMessage = "";
    
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(token);
        
        if (null == tokenResult) {
            resMessage = "access token invalid";
            resFlag = false;
        } else {
            //fetch token from redis
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
    
            String accessTokenRedis = strRedisTemplate.opsForValue().get(accessTokenKey);
            if ((StringUtils.isBlank(accessTokenRedis)) || !token.trim().equals(accessTokenRedis.trim())) {
                resMessage = "access token invalid";
                resFlag = false;
            }
        }
        
        if (!resFlag) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resMessage)).toString());
        }
    
        return resFlag;
    }
}
