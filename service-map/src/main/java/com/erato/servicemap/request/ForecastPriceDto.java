package com.erato.servicemap.request;

import lombok.Data;

/**
 * @author ZhangYuan
 * @date 2023/4/4
 */

@Data
public class ForecastPriceDto {
    
    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
}
