package com.erato.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.erato.internalcommon.dto.TokenResult;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public class JwtUtils {
    
    //salt
    private static final String SIGN = "erato!@##";
    private static final String JWT_KEY_PHONE = "phone";
    /**
     * passenger:1;  driver:2
     */
    private static final String JWT_KEY_IDENTITY = "identity";
    
    public static String generateToken(String passengerPhone, String identity) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
    
        //expire time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date expireTime = calendar.getTime();
    
        JWTCreator.Builder builder = JWT.create();
        
        //integrate map
           /*(k,v) -> {builder.withClaim(k,v)}*/
        map.forEach(builder::withClaim);
        
        //integrate expire time
//        builder.withExpiresAt(expireTime);
        
        //generate token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        
        
        return sign;
    }
    
    /**
     * parse token
     * @param token
     * @return
     */
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
    
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }
    
    public static void main(String[] args) {
    
        String s = generateToken("18374874458", "1");
        System.out.println("generated token:" + s);
    }
}
