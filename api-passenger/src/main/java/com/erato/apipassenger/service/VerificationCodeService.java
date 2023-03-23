package com.erato.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@Service
public class VerificationCodeService {
    
    public String generateCode(String passengerPhone) {
        
        // remote call verification-code service
        
        
        // store into redis
        
        
        //return
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("message", "success");
        return jsonObject.toString();
    }
}
