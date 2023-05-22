package com.erato.servicemap.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.erato.internalcommon.constant.AmapConfigConstants;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.response.AddSvcResp;
import com.erato.servicemap.response.AmapCommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用高德
 */

@Service
@Slf4j
public class ServiceClient {

    @Value("${amap.key}")
    private String amapKey;

    //在主启里已经bean过了
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 添加服务
     * @param name
     * @return
     */
    public ResponseResult<String> add(String name){
        // assemble url 组装url
        /*
         * https://restapi.a....
         */
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.SERVICE_ADD_URL);
        urlBuilder.append("?");
        urlBuilder.append("key="+amapKey);
        urlBuilder.append("&");
        urlBuilder.append("name="+name);

        log.info(urlBuilder.toString());

        ResponseEntity<String> forEntity = restTemplate.postForEntity(urlBuilder.toString(), null, String.class);
        String addSvcStr = forEntity.getBody();

        //这个包太小众了不用了 JSONObject jsonObject = JSONObject.fromObject(addSvcStr);
        //str -》 复杂java对象（对象嵌套对象）
        AmapCommonResp<AddSvcResp> addSvcCommResp = JSON.parseObject(addSvcStr, new TypeReference<AmapCommonResp<AddSvcResp>>() {
        });
        if (addSvcCommResp.getData() == null) {
            return ResponseResult.fail("高德错误");
        }
        String sid = addSvcCommResp.getData().getSid();
        return ResponseResult.success(sid);
    }
}
