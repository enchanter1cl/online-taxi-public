package com.erato.servicemap.controller;


import com.erato.internalcommon.dto.ResponseResult;
import com.erato.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerminalController {

    @Autowired
    TerminalService terminalService;

    public ResponseResult add() {
        return terminalService.add();
    }
}
