package com.erato.internalcommon.request;

import lombok.Data;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@Data
public class VerificationCodeDTO {
    
    private String passengerPhone;
    private String verificationCode;
    private String driverPhone;
}
