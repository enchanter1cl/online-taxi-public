package com.erato.servicedriveruser.dao;

import com.erato.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DriverUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-21 23:33:01
 */

@Mapper
public interface DriverUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DriverUser queryById(Long id);

    /*
     * 查询指定行数据
     *
     * @param driverUser 查询条件
     * @return 对象列表
     */
    List<DriverUser> queryAllByLimit(DriverUser driverUser);

    /**
     * 统计总行数
     *
     * @param driverUser 查询条件
     * @return 总行数
     */
    long count(DriverUser driverUser);

    /**
     * 新增数据
     *
     * @param driverUser 实例对象
     * @return 影响行数
     */
    int insert(DriverUser driverUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DriverUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DriverUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DriverUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DriverUser> entities);

    /**
     * 修改数据
     *
     * @param driverUser 实例对象
     * @return 影响行数
     */
    int update(DriverUser driverUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

