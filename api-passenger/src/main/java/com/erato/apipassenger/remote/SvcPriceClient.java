package com.erato.apipassenger.remote;

import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-price")
public interface SvcPriceClient {

    @PostMapping("/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
