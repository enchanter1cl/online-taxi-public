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

    TOKEN_ERROR(1199, "token error"),

    PRICE_RULE_EMPTY(1300, "计价规则不存在")
    ;
    
    @Getter
    private int code;
    @Getter
    private String value;
    
    
}
