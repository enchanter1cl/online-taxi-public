package com.erato.service.price.service.impl;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.ForecastPriceResponse;
import com.erato.service.price.service.ForecastPriceService;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);

        return ResponseResult.success(forecastPriceResponse);
    }
}
