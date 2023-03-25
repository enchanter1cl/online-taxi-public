package com.erato.internalcommon.dto;

import com.erato.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ZhangYuan
 * @date 2023/3/25
 */

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    
    private int code;
    private String message;
    private T data;
    
    
    
    public static <T> ResponseResult success() {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }
    
    /**
     * custom status
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }
    
    /**
     * custom status
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
    
    /**
     * other fail.  偷懒，兜底
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);
    }
}
