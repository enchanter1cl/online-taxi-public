package com.erato.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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
    private static final String JWT_KEY = "passengerPhone";
    
    public static String generateToken(String passengerPhone) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY, passengerPhone);
    
        //expire time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date expireTime = calendar.getTime();
    
        JWTCreator.Builder builder = JWT.create();
        
        //integrate map
           /*(k,v) -> {builder.withClaim(k,v)}*/
        map.forEach(builder::withClaim);
        
        //integrate expire time
        builder.withExpiresAt(expireTime);
        
        //generate token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        
        
        return sign;
    }
    
    /**
     * parse token
     * @param token
     * @return
     */
    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }
    
    public static void main(String[] args) {
    
        String s = generateToken("18374874458");
        System.out.println("generated token:" + s);
    }
}
