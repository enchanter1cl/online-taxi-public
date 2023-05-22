package com.erato.apidriver.service;

import com.erato.apidriver.remote.SvcDriverUser;
import com.erato.apidriver.remote.SvcVerificationCode;
import com.erato.internalcommon.dto.ResponseResult;
import com.erato.internalcommon.response.DriverUserExistsResponse;
import com.erato.internalcommon.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    @Autowired
    SvcDriverUser svcDriverUser;
    @Autowired
    SvcVerificationCode svcVerificationCode;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {
        //1 feign 查询该司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsRespRes = svcDriverUser.checkDriver(driverPhone);
        DriverUserExistsResponse driverUserExistsResponse = driverUserExistsRespRes.getData();
        int ifExists = driverUserExistsResponse.getIsExists();
        if (ifExists == 0) {
            return  ResponseResult.fail(1501,"司机不存在");
        }


        //2 获取验证码
        ResponseResult<NumberCodeResponse> resp = svcVerificationCode.numberCode(6);
        NumberCodeResponse numberCodeResponse = resp.getData();
        int numberCode = numberCodeResponse.getNumberCode();

        //3 sms发送验证码

        return ResponseResult.success(numberCode);
    }
}
