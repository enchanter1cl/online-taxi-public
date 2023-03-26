package com.erato.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resMessage = "token signature verification error";
            resFlag = false;
        } catch (TokenExpiredException e) {
            resMessage = "token expired error";
            resFlag = false;
        } catch (AlgorithmMismatchException e) {
            resMessage = "token algorithm mismatch error";
            resFlag = false;
        } catch (Exception e) {
            resMessage = "token invalid";
            resFlag = false;
        }
        
        if (null == tokenResult) {
            resMessage = "token invalid";
            resFlag = false;
        } else {
            //fetch token from redis
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generateTokenKey(phone, identity);
    
            String tokenRedis = strRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis)) {
                resMessage = "token invalid";
                resFlag = false;
            } else {
                if (!token.trim().equals(tokenRedis.trim())) {
                    resMessage = "token invalid";
                    resFlag = false;
                }
            }
        }
        
        
        if (!resFlag) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resMessage)).toString());
        }
    
        return resFlag;
    }
}
