package com.erato.servicedriveruser.controller;

import com.erato.internalcommon.dto.DriverUser;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicedriveruser.service.DriverUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DriverUser)表控制层
 *
 * @author makejava
 * @since 2023-05-21 23:33:00
 */
@RestController
//@RequestMapping("driver-user")
public class DriverUserController {
    /**
     * 服务对象
     */
    @Resource
    private DriverUserService driverUserService;

    @GetMapping("/check-driver/{phone}")
    public ResponseResult getUser(@PathVariable("phone")String phone) {

        return driverUserService.getDriverUserByPhone(phone);
    }

    /**
     * 分页查询
     *
     * @param driverUser  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
//    public ResponseEntity<Page<DriverUser>> queryByPage(DriverUser driverUser, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.driverUserService.queryByPage(driverUser, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DriverUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.driverUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param driverUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DriverUser> add(@RequestBody DriverUser driverUser) {
        System.out.println("---");
        return ResponseEntity.ok(this.driverUserService.insert(driverUser));
    }

    /**
     * 编辑数据
     *
     * @param driverUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DriverUser> edit(DriverUser driverUser) {
        return ResponseEntity.ok(this.driverUserService.update(driverUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.driverUserService.deleteById(id));
    }

}

