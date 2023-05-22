package com.erato.servicemap.response;

import lombok.Data;

@Data
public class AmapCommonResp<T> {

    private String errcode;
    private String errmsg;
    private String errdetail;

    private T data;
}
