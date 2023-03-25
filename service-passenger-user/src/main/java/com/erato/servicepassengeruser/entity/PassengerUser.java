package com.erato.servicepassengeruser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (PassengerUser)实体类
 *
 * @author zhangyuan
 * @since 2023-03-25 23:48:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerUser implements Serializable {
    private static final long serialVersionUID = -26413830718175434L;
    
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

