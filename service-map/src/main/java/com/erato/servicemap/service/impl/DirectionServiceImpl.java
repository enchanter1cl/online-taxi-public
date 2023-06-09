package com.erato.servicemap.service.impl;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.remote.MapDirectionClient;
import com.erato.servicemap.response.DirectionResponse;
import com.erato.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/4/4
 */
@Service
public class DirectionServiceImpl implements DirectionService {
    
    @Autowired
    MapDirectionClient mapDirectionClient;
    
    @Override
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        // 调用高德
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        System.out.println(direction);
    
        return ResponseResult.success().setData(direction);
    }
}
