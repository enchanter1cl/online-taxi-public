package com.erato.servicemap.service;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/4/4
 */

public interface DirectionService {
    ResponseResult driving(String depLongitude, String depLatitude, String desLongitude, String desLatitude);
}
