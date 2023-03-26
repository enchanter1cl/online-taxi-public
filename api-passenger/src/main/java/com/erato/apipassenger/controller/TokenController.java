package com.erato.apipassenger.controller;

import com.erato.apipassenger.service.RefreshTokenService;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
@RestController
public class TokenController {
    
    @Autowired
    RefreshTokenService refreshTokenService;
    
    @PostMapping("/token-refresh")
    ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
    
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        return refreshTokenService.refreshToken(refreshTokenSrc);
    }
    
}
