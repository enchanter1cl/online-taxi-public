package com.erato.serviceprice.service.impl;

import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DirectionResponse;
import com.erato.internalcommon.response.ForecastPriceResponse;
import com.erato.serviceprice.remote.ServiceMapClient;
import com.erato.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {
    @Autowired
    ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);

        return ResponseResult.success(forecastPriceResponse);
    }
}
