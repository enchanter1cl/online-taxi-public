package com.erato.internalcommon.util;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public class RedisPrefixUtils {
    /*passenger verification code prefix*/
    static String verificationCodePrefix = "passenger-verification-code-";
    /*token prefix*/
    static String tokenPrefix = "token-";
    
    /**
     * generate Key according to phoneNumber
     * @return
     */
    public static String generateVerificationCodeKey(String passengerPhone, String identity) {
        return verificationCodePrefix + identity + "-" + passengerPhone;
    }
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
