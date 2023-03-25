package com.erato.servicepassengeruser.service.impl;

import com.erato.servicepassengeruser.entity.PassengerUser;
import com.erato.servicepassengeruser.dao.PassengerUserDao;
import com.erato.servicepassengeruser.service.PassengerUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (PassengerUser)表服务实现类
 *
 * @author zhangyuan
 * @since 2023-03-25 23:48:57
 */
@Service("passengerUserService")
public class PassengerUserServiceImpl implements PassengerUserService {
    @Resource
    private PassengerUserDao passengerUserDao;
    
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PassengerUser queryById(Long id) {
        return this.passengerUserDao.queryById(id);
    }
    
    
    /**
     * 新增数据
     *
     * @param passengerUser 实例对象
     * @return 实例对象
     */
    @Override
    public PassengerUser insert(PassengerUser passengerUser) {
        this.passengerUserDao.insert(passengerUser);
        return passengerUser;
    }
    
    /**
     * 修改数据
     *
     * @param passengerUser 实例对象
     * @return 实例对象
     */
    @Override
    public PassengerUser update(PassengerUser passengerUser) {
        this.passengerUserDao.update(passengerUser);
        return this.queryById(passengerUser.getId());
    }
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.passengerUserDao.deleteById(id) > 0;
    }
}
