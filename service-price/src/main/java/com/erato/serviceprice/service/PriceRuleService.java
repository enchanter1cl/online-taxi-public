package com.erato.serviceprice.service;

import com.erato.internalcommon.dto.PriceRule;

/**
 * (PriceRule)表服务接口
 *
 * @author makejava
 * @since 2023-05-21 18:42:02
 */
public interface PriceRuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param cityCode 主键
     * @return 实例对象
     */
    PriceRule queryById(String cityCode);



    /**
     * 新增数据
     *
     * @param priceRule 实例对象
     * @return 实例对象
     */
    PriceRule insert(PriceRule priceRule);

    /**
     * 修改数据
     *
     * @param priceRule 实例对象
     * @return 实例对象
     */
    PriceRule update(PriceRule priceRule);

    /**
     * 通过主键删除数据
     *
     * @param cityCode 主键
     * @return 是否成功
     */
    boolean deleteById(String cityCode);

}
