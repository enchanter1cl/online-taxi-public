package com.erato.servicemap.service;

import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    TerminalClient terminalClient;

    public ResponseResult add() {
        return ResponseResult.success();
    }
}
