package com.erato.serviceprice.remote;

import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {

    @GetMapping("/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
