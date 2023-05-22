package com.erato.apidriver.remote;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-driver-user")
public interface SvcDriverUser {

    @GetMapping("/check-driver/{phone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("phone")String phone);
}
