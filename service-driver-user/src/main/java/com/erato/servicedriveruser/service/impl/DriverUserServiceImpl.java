package com.erato.servicedriveruser.service.impl;

import com.erato.internalcommon.dto.DriverUser;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicedriveruser.dao.DriverUserDao;
import com.erato.servicedriveruser.service.DriverUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DriverUser)表服务实现类
 *
 * @author makejava
 * @since 2023-05-21 23:33:02
 */
@Service("driverUserService")
public class DriverUserServiceImpl implements DriverUserService {
    @Resource
    private DriverUserDao driverUserDao;

    public ResponseResult getDriverUserByPhone(String phone) {

        DriverUser driverUserCondition = new DriverUser();
        driverUserCondition.setDriverPhone(phone);

        List<DriverUser> driverUsers = driverUserDao.queryAllByLimit(driverUserCondition);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(3099, "司机不存在");
        }
        DriverUser driverUserDb = driverUsers.get(0);

        /* controller来做 */
        //int isExists = 1;
//        if (driverUserDb == null) {}

        //wrap return resp
//        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
//        driverUserExistsResponse.setDriverPhone(driverUserCondition.getDriverPhone());
//        driverUserExistsResponse.setIsExists(isExists);

        return ResponseResult.success(driverUserDb);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DriverUser queryById(Long id) {
        return this.driverUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param driverUser  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<DriverUser> queryByPage(DriverUser driverUser, PageRequest pageRequest) {
//        long total = this.driverUserDao.count(driverUser);
//        return new PageImpl<>(this.driverUserDao.queryAllByLimit(driverUser, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param driverUser 实例对象
     * @return 实例对象
     */
    @Override
    public DriverUser insert(DriverUser driverUser) {
        System.out.println(driverUser);
        this.driverUserDao.insert(driverUser);
        return driverUser;
    }

    /**
     * 修改数据
     *
     * @param driverUser 实例对象
     * @return 实例对象
     */
    @Override
    public DriverUser update(DriverUser driverUser) {
        this.driverUserDao.update(driverUser);
        return this.queryById(driverUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.driverUserDao.deleteById(id) > 0;
    }
}
