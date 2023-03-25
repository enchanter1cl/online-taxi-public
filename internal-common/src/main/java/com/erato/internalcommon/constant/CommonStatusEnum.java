package com.erato.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@AllArgsConstructor
public enum CommonStatusEnum {

    SUCCESS(1,"success"),

    FAIL(0, "fail"),
    
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),
    ;
    
    @Getter
    private int code;
    @Getter
    private String value;
    
    
}
