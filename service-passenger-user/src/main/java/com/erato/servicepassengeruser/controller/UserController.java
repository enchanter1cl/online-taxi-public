package com.erato.servicepassengeruser.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@RestController
public class UserController {
    
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO) {
    
        return ResponseResult.success();
    }
}
