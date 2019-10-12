package cn.freemadao.exception;


import cn.freemadao.dto.Result;
import cn.freemadao.enums.ResultEnums;
import cn.freemadao.enums.StatusCode;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕捉异常----@ControllerAdvice
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    //权限不足触发这个
    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorizedExceptionHandler() {
        return  new Result(StatusCode.ACCESS_ERROR, ResultEnums.ACCESS_ERROR.getInfo());
    }
}
