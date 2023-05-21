package com.erato.servicemap.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.request.ForecastPriceDto;
import com.erato.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 距离 controller
 * @author ZhangYuan
 * @date 2023/4/4
 */

@RestController
@RequestMapping("/direction")
public class DirectionController {
    
    @Autowired
    DirectionService directionService;

    /**
     * 路径规划
     * @param forecastPriceDto
     * @return
     */
    @GetMapping("/driving")
    public ResponseResult direction(@RequestBody ForecastPriceDto forecastPriceDto) {
    
        String depLongitude = forecastPriceDto.getDepLongitude();
        String depLatitude = forecastPriceDto.getDepLatitude();
        String desLongitude = forecastPriceDto.getDesLongitude();
        String desLatitude = forecastPriceDto.getDesLatitude();
    
        return directionService.driving(depLongitude, depLatitude, desLongitude, desLatitude);
    }
}
