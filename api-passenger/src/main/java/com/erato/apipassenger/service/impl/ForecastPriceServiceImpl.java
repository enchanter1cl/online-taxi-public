package com.erato.apipassenger.service.impl;

import com.erato.apipassenger.remote.SvcPriceClient;
import com.erato.apipassenger.service.ForecastService;
import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/5/21
 */

@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastService {

    @Autowired
    SvcPriceClient svcPriceClient;
    
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

        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<ForecastPriceResponse> forecast = svcPriceClient.forecastPrice(forecastPriceDTO);
        double price = forecast.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }
}
