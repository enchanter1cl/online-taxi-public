package com.erato.serviceprice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (PriceRule)实体类
 *
 * @author makejava
 * @since 2023-05-21 18:42:01
 */
@Data
public class PriceRuleDepricated implements Serializable {
    private static final long serialVersionUID = 798286021656916079L;

    private String cityCode;

    private String vehicleType;

    private Double startFare;

    private Integer startMile;

    private Double unitPricePerMile;

    private Double unitPricePerMinute;


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getStartFare() {
        return startFare;
    }

    public void setStartFare(Double startFare) {
        this.startFare = startFare;
    }

    public Integer getStartMile() {
        return startMile;
    }

    public void setStartMile(Integer startMile) {
        this.startMile = startMile;
    }

    public Double getUnitPricePerMile() {
        return unitPricePerMile;
    }

    public void setUnitPricePerMile(Double unitPricePerMile) {
        this.unitPricePerMile = unitPricePerMile;
    }

    public Double getUnitPricePerMinute() {
        return unitPricePerMinute;
    }

    public void setUnitPricePerMinute(Double unitPricePerMinute) {
        this.unitPricePerMinute = unitPricePerMinute;
    }

}

