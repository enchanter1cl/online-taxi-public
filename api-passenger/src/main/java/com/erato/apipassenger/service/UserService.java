package com.erato.apipassenger.service;

import com.erato.internalcommon.dto.ResponseResult;

/**
 * @author ZhangYuan
 * @date 2023/3/27
 */

public interface UserService {
    ResponseResult getUserByAccessToken(String accessToken);
}
