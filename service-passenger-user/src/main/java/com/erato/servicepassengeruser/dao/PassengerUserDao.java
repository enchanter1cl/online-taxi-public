package com.erato.servicepassengeruser.dao;

import com.erato.servicepassengeruser.entity.PassengerUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (PassengerUser)表数据库访问层
 *
 * @author zhangyuan
 * @since 2023-03-25 23:48:49
 */

@Mapper
public interface PassengerUserDao {
    
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PassengerUser queryById(Long id);
    
    PassengerUser queryByPhone(String passengerPhone);
    
    /**
     * 统计总行数
     *
     * @param passengerUser 查询条件
     * @return 总行数
     */
    long count(PassengerUser passengerUser);
    
    /**
     * 新增数据
     *
     * @param passengerUser 实例对象
     * @return 影响行数
     */
    int insert(PassengerUser passengerUser);
    
    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PassengerUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PassengerUser> entities);
    
    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PassengerUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PassengerUser> entities);
    
    /**
     * 修改数据
     *
     * @param passengerUser 实例对象
     * @return 影响行数
     */
    int update(PassengerUser passengerUser);
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    
}

