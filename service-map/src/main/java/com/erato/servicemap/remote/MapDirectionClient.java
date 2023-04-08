package com.erato.servicemap.remote;

import com.erato.internalcommon.constant.AmapConfigConstants;
import com.erato.servicemap.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangYuan
 * @date 2023/4/4
 */

@Service
@Slf4j
public class MapDirectionClient {
    
    @Value("${amap.key}")
    private String ampKey;
    
    @Autowired
    RestTemplate restTemplate;
    
    public DirectionResponse direction(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {
        // assemble url
        /*
        * https://restapi.amap.com/v3/direction/driving?origin=116.45925,39.910031&destination=116.587922,40.081577&output=xml&key=<用户的key>
        */
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin="+depLongitude+","+depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination="+desLongitude+","+desLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extension=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + ampKey);
        
        log.info(urlBuilder.toString());
        
        // call api
        ResponseEntity<String> directionResp = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionStr = directionResp.getBody();
        log.info("directionResp.body:{}", directionResp.getBody());
        
        // parse result
        DirectionResponse directionResponse = parseDirectionEntity(directionStr);
    
    
        return directionResponse;
    }
    
    private DirectionResponse parseDirectionEntity(String directionStr) {
    
        DirectionResponse directionResponse = null;
        try {
        
            //str -> json obj   最外层
            JSONObject result = JSONObject.fromObject(directionStr);
            if (result.has("status")) {
                int status = result.getInt("status");
                if (status == 1) {
                    if (result.has("route")) {
                        JSONObject routeObj = result.getJSONObject("route");
                        JSONArray pathsArr = routeObj.getJSONArray("paths");
                        JSONObject pathObj = pathsArr.getJSONObject(0);
                        directionResponse = new DirectionResponse();
                        if (pathObj.has("distance")) {
                            int distance = pathObj.getInt("distance");
                            directionResponse.setDistance(distance);
                        }
                        if (pathObj.has("duration")) {
                            int duration = pathObj.getInt("duration");
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
            
    
        } catch (Exception e) {
        
        }
        
        return directionResponse;
    }
}
