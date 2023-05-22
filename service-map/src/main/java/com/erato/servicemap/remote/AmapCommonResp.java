package com.erato.servicemap.remote;

import lombok.Data;

@Data
public class AmapCommonResp<T> {

    private String errcode;
    private String errmsg;
    private String errdetail;

    private T data;
}
