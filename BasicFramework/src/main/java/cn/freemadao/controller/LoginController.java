package cn.freemadao.controller;


import cn.freemadao.common.Base;
import cn.freemadao.dto.Result;
import cn.freemadao.entity.User;
import cn.freemadao.enums.ResultEnums;
import cn.freemadao.enums.StatusCode;
import cn.freemadao.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public Result login(@RequestParam(value = "userName", required = false) String userName,
                        @RequestParam(value = "password", required = false) String password) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> map = new HashMap<>();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            //开始验证，这一步会跳转到自定义Realm
            subject.login(token);
            map.put(Base.TOKEN,userName);
            //设置session
            User currentUser = userService.findByUserName(userName);
            subject.getSession().setAttribute(Base.CURRENT_USER, currentUser);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.LOGINE_RROR, ResultEnums.LOGIN_ERROR.getInfo());
        }
        return new Result(StatusCode.SUCCESS, map);
    }

    @RequestMapping(value = "/logout")
    public Result logout() {
        try{
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e){
            return new Result(StatusCode.ERROR, ResultEnums.INNER_ERROR);
        }
        return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
    }

    @RequestMapping(value = "/getInfo")
    public Result getInfo() {
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
        String userName = user.getUserName();
        JSONObject userPermission = userService.getPermission(userName);
        return new Result(StatusCode.SUCCESS, userPermission);
    }

}
