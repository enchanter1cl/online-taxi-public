package com.erato.internalcommon.dto;

import lombok.Data;

/**
 * @author ZhangYuan
 * @date 2023/5/21
 */

@Data
public class ForecastPriceDTO {

    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
}
