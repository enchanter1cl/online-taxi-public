package com.erato.serviceverificationcode.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@RestController
public class VerificationCodeController {
    
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        double randomDouble = (Math.random()*9+1)*Math.pow(10,size-1);
        int randomCode = (int)randomDouble;
    
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(randomCode);
        
        return ResponseResult.success().setData(numberCodeResponse);
    }
}
