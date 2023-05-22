package com.erato.servicemap.service;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFromMapService {

    @Autowired
    ServiceClient serviceClient;

    public ResponseResult add(String name) {
        return serviceClient.add(name);
    }
}
