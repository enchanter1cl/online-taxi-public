package com.erato.apipassenger.service.impl;

import com.erato.apipassenger.service.ForecastService;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.ForecastPriceResponse;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/5/21
 */

@Service
public class ForecastPriceServiceImpl implements ForecastService {
    
    /**
     * 根据出发地目的地经纬度 预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
    
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }
}
