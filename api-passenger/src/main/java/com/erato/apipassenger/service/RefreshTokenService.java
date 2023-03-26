package com.erato.apipassenger.service;

import com.erato.internalcommon.dto.ResponseResult;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
public interface RefreshTokenService {
    
    ResponseResult refreshToken(String refreshTokenSrc);
}
