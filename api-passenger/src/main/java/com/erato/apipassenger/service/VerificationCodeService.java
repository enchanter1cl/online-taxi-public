package com.erato.apipassenger.service;

import com.erato.apipassenger.remote.SvcVerificationCodeClient;
import com.erato.internalcommon.constant.CommonStatusEnum;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.NumberCodeResponse;
import com.erato.internalcommon.response.TokenResponse;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    SvcPassengerUserClient svcPassengerUserClient;
    String verificationCodePrefix = "passenger-verification-code-";
    @Autowired
    StringRedisTemplate strRedisTemplate;
    
    public ResponseResult generateCode(String passengerPhone) {
        // remote call verification-code service
        ResponseResult<NumberCodeResponse> numberCodeResp = svcVerificationCodeClient.getNumberCode();
        int numberCode = numberCodeResp.getData().getNumberCode();
    
        // store into redis
        String key = generateKey(passengerPhone);
        strRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);
        
        //send message text  腾讯 阿里 华信 容联..
        
        return ResponseResult.success();
    }
    
    /**
     * generate Key according to phoneNumber
     * @return
     */
    private String generateKey(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }
    
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        
        // read phone's verification code from redis
        String key = generateKey(passengerPhone);
        String redisCode = strRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(redisCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
           //trim()用于删除字符串的头尾空白符
        if (!verificationCode.trim().equals(redisCode.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // judge if there already exist this user
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        svcPassengerUserClient.loginOrRegister(verificationCodeDTO);

        // issue token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success().setData(tokenResponse);
    }
}
