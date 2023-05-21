package com.erato.apidriver.controller;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver-user")
public class UserController {

    @PutMapping
    public ResponseResult<String> updateDriverUser() {
        return ResponseResult.success("hello");
    }
}
