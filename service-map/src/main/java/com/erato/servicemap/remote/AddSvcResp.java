package com.erato.servicemap.remote;

import lombok.Data;

/**
 * 自创  调用高德添加服务的返回body
 */
@Data
public class AddSvcResp {

    private String name;
    /**
     * 服务id
     */
    private String sid;
}
