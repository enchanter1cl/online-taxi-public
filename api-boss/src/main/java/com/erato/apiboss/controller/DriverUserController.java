package com.erato.apiboss.controller;

import com.erato.internalcommon.dto.DriverUser;
import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {
        return ResponseResult.success("hello");
    }
}
