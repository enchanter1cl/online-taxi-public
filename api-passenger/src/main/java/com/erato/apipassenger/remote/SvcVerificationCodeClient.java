package com.erato.apipassenger.remote;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@FeignClient("service-verification-code")
public interface SvcVerificationCodeClient {
    
    @GetMapping("/numberCode/6")
    ResponseResult<NumberCodeResponse> getNumberCode();
}
