package com.erato.apipassenger.service;

import com.erato.apipassenger.remote.SvcVerificationCodeClient;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@Service
public class VerificationCodeService {
    
    @Autowired
    SvcVerificationCodeClient svcVerificationCodeClient;
    String verificationCodePrefix = "passenger-verification-code-";
    @Autowired
    StringRedisTemplate strRedisTemplate;
    
    public ResponseResult generateCode(String passengerPhone) {
        // remote call verification-code service
        ResponseResult<NumberCodeResponse> numberCodeResp = svcVerificationCodeClient.getNumberCode();
        int numberCode = numberCodeResp.getData().getNumberCode();
    
        // store into redis
        String key = verificationCodePrefix + passengerPhone;
        strRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);
        
        //send message text  腾讯 阿里 华信 容联..
        
        return ResponseResult.success();
    }
}
