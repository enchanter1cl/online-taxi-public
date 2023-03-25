package com.erato.servicepassengeruser.service;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@Service
public class UserService {
    
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("i m called");
        return ResponseResult.success();
    }
}
