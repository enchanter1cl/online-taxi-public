package com.erato.serviceprice.controller;

import com.erato.serviceprice.entity.PriceRule;
import com.erato.serviceprice.service.PriceRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PriceRule)表控制层
 *
 * @author makejava
 * @since 2023-05-21 18:42:01
 */
@RestController
@RequestMapping("priceRule")
public class PriceRuleController {
    /**
     * 服务对象
     */
    @Resource
    private PriceRuleService priceRuleService;



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<PriceRule> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.priceRuleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param priceRule 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<PriceRule> add(PriceRule priceRule) {
        return ResponseEntity.ok(this.priceRuleService.insert(priceRule));
    }

    /**
     * 编辑数据
     *
     * @param priceRule 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<PriceRule> edit(PriceRule priceRule) {
        return ResponseEntity.ok(this.priceRuleService.update(priceRule));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.priceRuleService.deleteById(id));
    }

}

