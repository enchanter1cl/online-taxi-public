package com.erato.apipassenger.service;

import com.erato.internalcommon.dto.ResponseResult;
/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public interface VerificationCodeService {
    ResponseResult generateCode(String passengerPhone);
    
    ResponseResult checkCode(String passengerPhone, String verificationCode);
}
