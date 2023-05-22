package com.erato.apipassenger.service.impl;

import com.erato.apipassenger.remote.SvcPassengerUserClient;
import com.erato.apipassenger.remote.SvcVerificationCodeClient;
import com.erato.apipassenger.service.VerificationCodeService;
import com.erato.internalcommon.constant.CommonStatusEnum;
import com.erato.internalcommon.constant.IdentityConstant;
import com.erato.internalcommon.constant.TokenConstant;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import com.erato.internalcommon.response.NumberCodeResponse;
import com.erato.internalcommon.response.TokenResponse;
import com.erato.internalcommon.util.JwtUtils;
import com.erato.internalcommon.util.RedisPrefixUtils;
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
public class VerificationCodeServiceImpl implements VerificationCodeService {
    
    @Autowired
    SvcVerificationCodeClient svcVerificationCodeClient;
    @Autowired
    SvcPassengerUserClient svcPassengerUserClient;
    @Autowired
    StringRedisTemplate strRedisTemplate;
    
    @Override
    public ResponseResult generateCode(String passengerPhone) {
        // remote call verification-code service
        ResponseResult<NumberCodeResponse> numberCodeResp = svcVerificationCodeClient.getNumberCode();
        int numberCode = numberCodeResp.getData().getNumberCode();
    
        // store into redis
        String key = RedisPrefixUtils.generateVerificationCodeKey(passengerPhone, "1");
        strRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);
        
        //send message text  腾讯 阿里 华信 容联..
        
        return ResponseResult.success();
    }
    
    @Override
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        
        // read phone's verification code from redis
        String key = RedisPrefixUtils.generateVerificationCodeKey(passengerPhone, "1");
        String redisCode = strRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(redisCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
           //trim()用于删除字符串的头尾空白符
        if (!verificationCode.trim().equals(redisCode.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // judge if there already exist this user then sign in or sign up
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        svcPassengerUserClient.loginOrRegister(verificationCodeDTO);

        // issue token
           /*不该用魔法值，应该用constant*/
        String accessToken = JwtUtils.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        
        // store token into redis
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        strRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        strRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        
        //response
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success().setData(tokenResponse);
    }
}
