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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
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
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();

        //读取计价规则
        PriceRule priceRuleCondition = new PriceRule();
        priceRuleCondition.setCityCode("110000");
        priceRuleCondition.setVehicleType("1");
        List<PriceRule> priceRules = priceRuleDao.queryAllByLimit(priceRuleCondition);
        if (priceRules.isEmpty()) {
            ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离 时长 和计价规则，计算价格");
        Double price = getPrice(distance, duration, priceRule);


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);

        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离 时长 和计价规则，计算价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private Double getPrice(Integer distance, Integer duration, PriceRule priceRule) {

        BigDecimal price = new BigDecimal(0);

        // 1 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        // 2 里程费
          //2.1 总里程 单位米
        BigDecimal distanceDecimal = new BigDecimal(distance);
          //总里程 转为km 保留2位小数 四舍五入
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);

        //2.2 起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);

        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
          //最终收费的里程数（总不能司机倒贴钱）
        Double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        BigDecimal mileDecimal = new BigDecimal(mile);
          //计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
          //里程总价
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        // 3 时长费
        BigDecimal time = new BigDecimal(duration);
          //时长 转为分钟
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
          //计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPriceMinuteDecimal = new BigDecimal(unitPricePerMinute);
          //时长总费用
        BigDecimal timeFare = timeDecimal.multiply(unitPriceMinuteDecimal);
        price = price.add(timeFare).setScale(2, BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();
    }

//    public static void main(String[] args) {
//
//        PriceRule priceRule = new PriceRule();
//        priceRule.setStartFare(10.00);
//        priceRule.setStartMile(3);
//        priceRule.setUnitPricePerMile(1.80);
//        priceRule.setUnitPricePerMinute(0.50);
//
//        Double price = getPrice(6500, 1800, priceRule);
//        System.out.println(price);
//
//    }
}
