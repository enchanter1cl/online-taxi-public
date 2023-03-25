package com.erato.servicepassengeruser.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import com.erato.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
    
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhone:"+ passengerPhone);
        
        return userService.loginOrRegister(passengerPhone);
    }
}
