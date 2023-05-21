package com.erato.servicedriveruser.entity;

import java.io.Serializable;

/**
 * (DriverUser)实体类
 *
 * @author makejava
 * @since 2023-05-21 23:33:01
 */
public class DriverUserDepricated implements Serializable {
    private static final long serialVersionUID = 835854443354457307L;

    private Long id;
    /**
     * 注册地行政区划代码
     */
    private String address;

    private String drverName;

    private String driverPhone;

    private Integer driverGender;

    private String licenseId;

    private String contractCompany;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDrverName() {
        return drverName;
    }

    public void setDrverName(String drverName) {
        this.drverName = drverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Integer getDriverGender() {
        return driverGender;
    }

    public void setDriverGender(Integer driverGender) {
        this.driverGender = driverGender;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getContractCompany() {
        return contractCompany;
    }

    public void setContractCompany(String contractCompany) {
        this.contractCompany = contractCompany;
    }

}

