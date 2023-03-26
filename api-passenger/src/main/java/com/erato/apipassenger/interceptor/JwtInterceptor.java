package com.erato.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.dto.TokenResult;
import com.erato.internalcommon.util.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.PrintWriter;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public class JwtInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean resFlag = true;
        String resMessage = "";
    
        String token = request.getHeader("Authorization");
        try {
            TokenResult tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resMessage = "token signature verification error";
            resFlag = false;
        } catch (TokenExpiredException e) {
            resMessage = "token expiired error";
            resFlag = false;
        } catch (AlgorithmMismatchException e) {
            resMessage = "token algorithm mismatch error";
            resFlag = false;
        } catch (Exception e) {
            resMessage = "token invalid";
            resFlag = false;
        }
        
        if (resFlag) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resMessage)).toString());
        }
    
        return true;
    }
}
