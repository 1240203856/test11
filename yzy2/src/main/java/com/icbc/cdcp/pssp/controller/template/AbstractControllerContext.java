package com.icbc.cdcp.pssp.controller.template;

import com.icbc.cdcp.pssp.exception.MyException;
import com.icbc.cdcp.pssp.vo.ResultVo;

public interface AbstractControllerContext {
    ResultVo call() throws MyException;
}
