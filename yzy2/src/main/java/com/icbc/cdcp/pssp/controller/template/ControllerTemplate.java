package com.icbc.cdcp.pssp.controller.template;

import com.icbc.cdcp.pssp.exception.MyException;
import com.icbc.cdcp.pssp.vo.ResultVo;
import org.springframework.stereotype.Component;

@Component
public class ControllerTemplate {

    public ResultVo call(AbstractControllerContext context) {
        long start = System.currentTimeMillis();

        try {
            return context.call();
        } catch (MyException e) {

            // 处理异常
            ResultVo resultVo = new ResultVo();
            resultVo.setData(null);
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setSuccess(false);

            return resultVo;

        } catch (Exception e) {
            // 处理异常
            ResultVo resultVo = new ResultVo();
            resultVo.setData(null);
            resultVo.setMessage(e.getMessage());
            resultVo.setSuccess(false);

            return resultVo;
        } finally {
            long end = System.currentTimeMillis();
        }
    }
}
