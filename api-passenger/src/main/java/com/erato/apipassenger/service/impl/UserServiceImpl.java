package com.erato.apipassenger.service.impl;

import com.erato.apipassenger.service.UserService;
import com.erato.internalcommon.dto.PassengerUser;
import com.erato.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYuan
 * @date 2023/3/27
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    @Override
    public ResponseResult getUserByAccessToken(String accessToken){
    
        log.info("accessToken:{}", accessToken);
        //parse access token, get phone
        
        //phone -> user
    
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("zhangsan");
        return ResponseResult.success();
    }
}
