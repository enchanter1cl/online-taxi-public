package com.erato.serviceprice.service;

import com.erato.internalcommon.dto.ResponseResult;

public interface ForecastPriceService {

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude);
}
