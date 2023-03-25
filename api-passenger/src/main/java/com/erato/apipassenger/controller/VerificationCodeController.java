package com.erato.apipassenger.controller;

import com.erato.internalcommon.request.VerificationCodeDTO;
import com.erato.apipassenger.service.VerificationCodeService;
import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangYuan
 * @date 2023/3/23
 */
@RestController
public class VerificationCodeController {
    
    @Autowired
    VerificationCodeService verificationCodeService;
    
    @GetMapping("verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
    
        return verificationCodeService.generateCode(verificationCodeDTO.getPassengerPhone());
    }
    @PostMapping("verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody  VerificationCodeDTO verificationCodeDTO){
    
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
    
        System.out.println("received+"+passengerPhone+verificationCode);
        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }
    
}
