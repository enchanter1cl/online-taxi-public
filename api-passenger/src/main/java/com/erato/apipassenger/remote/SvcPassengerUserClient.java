package com.erato.apipassenger.remote;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangYuan
 * @date 2023/3/26
 */
@FeignClient("service-passenger-user")
public interface SvcPassengerUserClient {
    
    @PostMapping("/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
