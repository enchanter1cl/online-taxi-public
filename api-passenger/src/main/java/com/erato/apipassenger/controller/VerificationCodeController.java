package com.erato.apipassenger.controller;

import com.erato.apipassenger.request.VerificationCodeDTO;
import com.erato.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@RestController
public class VerificationCodeController {
    
    @Autowired
    VerificationCodeService verificationCodeService;
    
    @GetMapping("verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
    
        return verificationCodeService.generateCode(verificationCodeDTO.getPassengerPhone());
    }
    
}
