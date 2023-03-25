package com.erato.servicepassengeruser.service;

import com.erato.servicepassengeruser.entity.PassengerUser;

/**
 * (PassengerUser)表服务接口
 *
 * @author zhangyuan
 * @since 2023-03-25 23:48:57
 */
public interface PassengerUserService {
    
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PassengerUser queryById(Long id);
    
    /**
     * 新增数据
     *
     * @param passengerUser 实例对象
     * @return 实例对象
     */
    PassengerUser insert(PassengerUser passengerUser);
    
    /**
     * 修改数据
     *
     * @param passengerUser 实例对象
     * @return 实例对象
     */
    PassengerUser update(PassengerUser passengerUser);
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    
}
