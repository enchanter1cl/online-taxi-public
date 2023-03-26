package com.erato.apipassenger.controller;

import com.erato.apipassenger.service.UserService;
import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangYuan
 * @date 2023/3/27
 */
@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    ResponseResult getUser(HttpServletRequest request) {
    
        //get access token from http request
        String accessToken = request.getHeader("Authorization");
    
        //query user info by access token
        return userService.getUserByAccessToken(accessToken);
    }
}
