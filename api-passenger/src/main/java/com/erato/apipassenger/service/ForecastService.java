package com.erato.apipassenger.service;

import com.erato.internalcommon.dto.ResponseResult;

/**
 * @author ZhangYuan
 * @date 2023/5/21
 */
public interface ForecastService {
    
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude);
}
