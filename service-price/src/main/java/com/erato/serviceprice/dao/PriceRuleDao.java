package com.erato.serviceprice.dao;

import com.erato.serviceprice.entity.PriceRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PriceRule)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-21 18:42:01
 */

@Mapper
public interface PriceRuleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cityCode 主键
     * @return 实例对象
     */
    PriceRule queryById(String cityCode);


    /**
     * 统计总行数
     *
     * @param priceRule 查询条件
     * @return 总行数
     */
    long count(PriceRule priceRule);

    /**
     * 新增数据
     *
     * @param priceRule 实例对象
     * @return 影响行数
     */
    int insert(PriceRule priceRule);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PriceRule> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PriceRule> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PriceRule> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PriceRule> entities);

    /**
     * 修改数据
     *
     * @param priceRule 实例对象
     * @return 影响行数
     */
    int update(PriceRule priceRule);

    /**
     * 通过主键删除数据
     *
     * @param cityCode 主键
     * @return 影响行数
     */
    int deleteById(String cityCode);

}

