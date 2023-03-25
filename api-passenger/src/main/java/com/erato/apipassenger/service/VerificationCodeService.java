package com.erato.apipassenger.service;

import com.erato.apipassenger.remote.SvcVerificationCodeClient;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@Service
public class VerificationCodeService {
    
    @Autowired
    SvcVerificationCodeClient svcVerificationCodeClient;
    
    public String generateCode(String passengerPhone) {
        
        // remote call verification-code service
        ResponseResult<NumberCodeResponse> numberCodeResp = svcVerificationCodeClient.getNumberCode();
        int numberCode = numberCodeResp.getData().getNumberCode();
        System.out.println("numberCode is: " + numberCode);
        
    
        // store into redis
        
        
        //return
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("message", "success");
        return jsonObject.toString();
    }
}
