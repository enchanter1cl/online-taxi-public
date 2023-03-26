package com.erato.apipassenger.controller;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */

@RestController
public class TestController {
    
    /**
     * uri that need token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest() {
        return ResponseResult.success().setData("auth test");
    }
    
    /**
     * uri that don't need token
     * @return
     */
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest() {
        return ResponseResult.success().setData("on auth test");
    }
}
