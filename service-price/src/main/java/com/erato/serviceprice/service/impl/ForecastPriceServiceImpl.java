package com.erato.serviceprice.service.impl;

import com.erato.internalcommon.constant.CommonStatusEnum;
import com.erato.internalcommon.dto.ForecastPriceDTO;
import com.erato.internalcommon.dto.PriceRule;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DirectionResponse;
import com.erato.internalcommon.response.ForecastPriceResponse;
import com.erato.serviceprice.dao.PriceRuleDao;
import com.erato.serviceprice.remote.ServiceMapClient;
import com.erato.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {
    @Autowired
    ServiceMapClient serviceMapClient;
    @Autowired
    PriceRuleDao priceRuleDao;

    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        // 距离和时长
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);

        //读取计价规则
        PriceRule priceRuleCondition = new PriceRule();
        priceRuleCondition.setCityCode("110000");
        priceRuleCondition.setVehicleType("1");
        List<PriceRule> priceRules = priceRuleDao.queryAllByLimit(priceRuleCondition);
        if (priceRules.isEmpty()) {
            ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);

        return ResponseResult.success(forecastPriceResponse);
    }
}
