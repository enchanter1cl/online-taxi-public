package com.erato.apipassenger.service.impl;

import com.erato.apipassenger.service.RefreshTokenService;
import com.erato.internalcommon.constant.CommonStatusEnum;
import com.erato.internalcommon.constant.TokenConstant;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.dto.TokenResult;
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
 * @date 2023/3/26
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    
    @Autowired
    StringRedisTemplate strRedisTemplate;
    @Override
    public ResponseResult refreshToken(String refreshTokenSrc) {
        
        //parse refreshTokenSrc
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (null == tokenResult) {
            ResponseResult.fail().setCode(CommonStatusEnum.TOKEN_ERROR.getCode()).setMessage(CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
    
        //fetch redisRefreshToken
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = strRedisTemplate.opsForValue().get(refreshTokenKey);
    
        //check refresh token
        if ((StringUtils.isBlank(refreshTokenRedis)) || !refreshTokenSrc.trim().equals(refreshTokenRedis.trim())) {
            ResponseResult.fail().setCode(CommonStatusEnum.TOKEN_ERROR.getCode()).setMessage(CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        
        //generate double token
        String refreshTokenNew = JwtUtils.generateToken(phone, identity, TokenConstant.REFRESH_TOKEN_TYPE);
        String accessTokenNew = JwtUtils.generateToken(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
    
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
        
        strRedisTemplate.opsForValue().set(refreshTokenKey, refreshTokenNew, 31, TimeUnit.DAYS);
        strRedisTemplate.opsForValue().set(accessTokenKey, accessTokenNew, 30, TimeUnit.DAYS);
        
        //return
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshTokenNew);
        tokenResponse.setAccessToken(accessTokenNew);
        return ResponseResult.success().setData(tokenResponse);
    }
}
