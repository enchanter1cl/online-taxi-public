package com.erato.internalcommon.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author ZhangYuan
 * @date 2023/3/27
 */

@Data
public class PassengerUser {
    
    private Long id;
    
    private LocalDateTime gmtCreate;
    
    private LocalDateTime gmtModified;
    
    private String passengerPhone;
    
    private String passengerName;
    /**
     * 0：未知，1：男，2：女
     */
    private Byte passengerGender;
    /**
     * 0：有效，1：失效
     */
    private Byte state;
    /**
     * 头像图片地址的url
     */
    private String profilePhoto;
}
