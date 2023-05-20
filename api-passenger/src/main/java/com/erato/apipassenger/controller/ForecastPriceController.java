package com.erato.apipassenger.controller;

import com.erato.apipassenger.service.ForecastService;
import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYuan
 * @date 2023/5/20
 */

@RestController
@Slf4j
public class ForecastPriceController {
    
    @Autowired
    ForecastService forecastService;
    
    @PostMapping("forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {
        log.info("depart", forecastPriceDTO.getDepLongitude());
    
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLatitude1 = forecastPriceDTO.getDestLatitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
    
    
        return forecastService.forecastPrice(depLongitude, depLatitude,destLatitude1, destLatitude);
    }
}
