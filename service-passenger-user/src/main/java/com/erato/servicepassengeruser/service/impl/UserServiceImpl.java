package com.erato.servicepassengeruser.service.impl;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicepassengeruser.dao.PassengerUserDao;
import com.erato.servicepassengeruser.entity.PassengerUser;
import com.erato.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

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
        PassengerUser resPassengerUser = passengerUserDao.queryByPhone(passengerPhone);
        // judge if user has already exist
        // if not exits, insert user info
        if (null == resPassengerUser){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("李四");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);
    
            LocalDateTime now  = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            
            passengerUserDao.insert(passengerUser);
        }
        
        
        return ResponseResult.success();
    }
    
}
