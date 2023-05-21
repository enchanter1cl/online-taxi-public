package com.erato.serviceprice.service.impl;

import com.erato.internalcommon.dto.PriceRule;
import com.erato.serviceprice.dao.PriceRuleDao;
import com.erato.serviceprice.service.PriceRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (PriceRule)表服务实现类
 *
 * @author makejava
 * @since 2023-05-21 18:42:02
 */
@Service
public class PriceRuleServiceImpl implements PriceRuleService {
    @Resource
    private PriceRuleDao priceRuleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cityCode 主键
     * @return 实例对象
     */
    @Override
    public PriceRule queryById(String cityCode) {
        return this.priceRuleDao.queryById(cityCode);
    }



    /**
     * 新增数据
     *
     * @param priceRule 实例对象
     * @return 实例对象
     */
    @Override
    public PriceRule insert(PriceRule priceRule) {
        this.priceRuleDao.insert(priceRule);
        return priceRule;
    }

    /**
     * 修改数据
     *
     * @param priceRule 实例对象
     * @return 实例对象
     */
    @Override
    public PriceRule update(PriceRule priceRule) {
        this.priceRuleDao.update(priceRule);
        return this.queryById(priceRule.getCityCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param cityCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String cityCode) {
        return this.priceRuleDao.deleteById(cityCode) > 0;
    }
}
