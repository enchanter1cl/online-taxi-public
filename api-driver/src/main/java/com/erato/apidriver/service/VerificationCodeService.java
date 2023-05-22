package com.erato.apidriver.service;

import com.erato.apidriver.remote.SvcDriverUser;
import com.erato.apidriver.remote.SvcVerificationCode;
import com.erato.internalcommon.constant.CommonStatusEnum;
import com.erato.internalcommon.constant.IdentityConstant;
import com.erato.internalcommon.constant.TokenConstant;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DriverUserExistsResponse;
import com.erato.internalcommon.response.NumberCodeResponse;
import com.erato.internalcommon.response.TokenResponse;
import com.erato.internalcommon.util.JwtUtils;
import com.erato.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    SvcDriverUser svcDriverUser;
    @Autowired
    SvcVerificationCode svcVerificationCode;
    @Autowired
    StringRedisTemplate strRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        //1 feign 查询该司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsRespRes = svcDriverUser.checkDriver(driverPhone);
        DriverUserExistsResponse driverUserExistsResponse = driverUserExistsRespRes.getData();
        int ifExists = driverUserExistsResponse.getIsExists();
        if (ifExists == 0) {
            return  ResponseResult.fail(1501,"司机不存在");
        }


        //2 获取验证码
        ResponseResult<NumberCodeResponse> resp = svcVerificationCode.numberCode(6);
        NumberCodeResponse numberCodeResponse = resp.getData();
        int numberCode = numberCodeResponse.getNumberCode();

        //3 sms发送验证码 阿里 腾讯 华信 容联

        //4 存入redis
        String key = RedisPrefixUtils.generateVerificationCodeKey(driverPhone, "2");
        strRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        return ResponseResult.success(numberCode);
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkCode(String driverPhone, String verificationCode) {

        // read phone's verification code from redis
        String key = RedisPrefixUtils.generateVerificationCodeKey(driverPhone, "2");
        String redisCode = strRedisTemplate.opsForValue().get(key);
          //redis里是否为空
        if (StringUtils.isBlank(redisCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
          //req里与redis里是否相等。 trim()用于删除字符串的头尾空白符
        if (!verificationCode.trim().equals(redisCode.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // judge if there already exist this user then sign in or sign up. 司机端不需要。之前已经check过了。
//        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
//        verificationCodeDTO.setPassengerPhone(driverPhone);
//        svcDriverUser.loginOrRegister(verificationCodeDTO);

        // issue token
        /*不该用魔法值，应该用constant*/
        String accessToken = JwtUtils.generateToken(driverPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(driverPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);

        // store token into redis
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        strRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        strRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        //response
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success().setData(tokenResponse);
    }
}
