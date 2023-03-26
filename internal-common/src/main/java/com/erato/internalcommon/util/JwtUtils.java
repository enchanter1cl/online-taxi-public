package com.erato.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
    
    private static final String JWT_KEY_TYPE = "tokenType";
    
    private static final String JWT_KEY_TIME = "";
    
    public static String generateToken(String passengerPhone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_KEY_TYPE, tokenType);
        //防止每次生成的 token 一样
        map.put(JWT_KEY_TIME, Calendar.getInstance().getTime().toString());
    
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
    
    /**
     * check token. Mainly to check if token has exception
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        boolean resFlag = true;
        String resMessage = "";
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resMessage = "token signature verification error";
            resFlag = false;
        } catch (TokenExpiredException e) {
            resMessage = "token expired error";
            resFlag = false;
        } catch (AlgorithmMismatchException e) {
            resMessage = "token algorithm mismatch error";
            resFlag = false;
        } catch (Exception e) {
            resMessage = "token invalid";
            resFlag = false;
        }
        return tokenResult;
    }
    
    public static void main(String[] args) {
    
        String s = generateToken("18374874458", "1", "access");
        System.out.println("generated token:" + s);
    }
}
