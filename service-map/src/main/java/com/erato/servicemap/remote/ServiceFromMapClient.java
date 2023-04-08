package com.erato.servicemap.remote;

import com.erato.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangYuan
 * @date 2023/4/4
 */
public class ServiceFromMapClient {
    
    @Value("${amap.key}")
    private String ampKey;
    
    @Autowired
    RestTemplate restTemplate;
    
    ResponseResult add() {
    
        StringBuilder stringBuilder = new StringBuilder();
    
        return null;
    }
}
