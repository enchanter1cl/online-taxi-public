package com.erato.servicemap.controller;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 调用高德 服务管理
 * @author ZhangYuan
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceFromMapService serviceFromMapService;

    /**
     * 创建高德服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    ResponseResult add(String name) {
        return serviceFromMapService.add(name);
    }
}
