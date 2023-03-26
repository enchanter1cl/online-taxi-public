package com.erato.servicepassengeruser.service.impl;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicepassengeruser.dao.PassengerUserDao;
import com.erato.servicepassengeruser.entity.PassengerUser;
import com.erato.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (PassengerUser)表服务实现类
 *
 * @author zhangyuan
 * @since 2023-03-25 23:48:57
 */
@Service("passengerUserService")
public class UserServiceImpl implements UserService {
    
    @Resource
    private PassengerUserDao passengerUserDao;
    public ResponseResult loginOrRegister(String passengerPhone) {
        // query user info by phone
        PassengerUser passengerUser = passengerUserDao.queryByPhone(passengerPhone);
        System.out.println("User:"+passengerUser);
        // judge if user already exist
        
        // if not exits, insert user info
        
        
        return ResponseResult.success();
    }
    
}
