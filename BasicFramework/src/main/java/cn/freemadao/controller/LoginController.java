package cn.freemadao.controller;


import cn.freemadao.dto.Result;
import cn.freemadao.enums.ResultEnums;
import cn.freemadao.enums.StatusCode;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping("/auth")
    public Result login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        Subject subject = SecurityUtils.getSubject();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //开始验证，这一步会跳转到自定义Realm
            subject.login(token);
        }catch (Exception e) {
//            e.printStackTrace();
            return new Result(StatusCode.LOGINE_RROR, ResultEnums.LOGIN_ERROR.getInfo());
        }
        return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
    }
}
