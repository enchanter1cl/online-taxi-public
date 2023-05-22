package com.erato.apidriver.service;

import com.erato.apidriver.remote.SvcDriverUser;
import com.erato.apidriver.remote.SvcVerificationCode;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DriverUserExistsResponse;
import com.erato.internalcommon.response.NumberCodeResponse;
import com.erato.internalcommon.util.RedisPrefixUtils;
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
}
