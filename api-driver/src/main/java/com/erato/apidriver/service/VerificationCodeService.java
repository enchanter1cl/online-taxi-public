package com.erato.apidriver.service;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        //1 feign 查询该司机是否存在

        //2 获取验证码

        //3 sms发送验证码

        return ResponseResult.success("hello");
    }
}
