package com.erato.servicedriveruser.service;


import com.erato.internalcommon.dto.DriverUser;

/**
 * (DriverUser)表服务接口
 *
 * @author makejava
 * @since 2023-05-21 23:33:02
 */
public interface DriverUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DriverUser queryById(Long id);

    /**
     * 分页查询
     *
     * @param driverUser  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    Page<DriverUser> queryByPage(DriverUser driverUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param driverUser 实例对象
     * @return 实例对象
     */
    DriverUser insert(DriverUser driverUser);

    /**
     * 修改数据
     *
     * @param driverUser 实例对象
     * @return 实例对象
     */
    DriverUser update(DriverUser driverUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
