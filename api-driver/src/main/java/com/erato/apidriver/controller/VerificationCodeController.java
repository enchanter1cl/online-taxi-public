package com.erato.apidriver.controller;

import com.erato.apidriver.service.VerificationCodeService;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String driverPhone = verificationCodeDTO.getDriverPhone();
        ResponseResult responseResult = verificationCodeService.checkAndSendVerificationCode(driverPhone);
        return ResponseResult.success(responseResult);
    }

    /**
     * 司机端校验验证码
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody  VerificationCodeDTO verificationCodeDTO){

        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println("received+"+driverPhone+verificationCode);
        return verificationCodeService.checkCode(driverPhone, verificationCode);
    }
}
