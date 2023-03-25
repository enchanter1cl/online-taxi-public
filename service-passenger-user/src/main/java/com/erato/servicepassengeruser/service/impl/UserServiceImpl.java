package com.erato.servicepassengeruser.service.impl;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicepassengeruser.entity.PassengerUser;
import com.erato.servicepassengeruser.dao.PassengerUserDao;
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
    
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("i m called");
        return ResponseResult.success();
    }
    @Resource
    private PassengerUserDao passengerUserDao;
}
