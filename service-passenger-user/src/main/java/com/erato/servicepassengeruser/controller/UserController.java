package com.erato.servicepassengeruser.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import com.erato.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
    
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        
        return userService.loginOrRegister(passengerPhone);
    }
}
