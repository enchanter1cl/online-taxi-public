package com.erato.apidriver.controller;

import com.erato.apidriver.service.VerificationCodeService;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String driverPhone = verificationCodeDTO.getDriverPhone();
        verificationCodeService.checkAndSendVerificationCode(driverPhone);
        return null;
    }
}
