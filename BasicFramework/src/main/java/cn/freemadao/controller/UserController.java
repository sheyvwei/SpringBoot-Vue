package cn.freemadao.controller;


import ch.qos.logback.core.status.Status;
import cn.freemadao.dto.Result;
import cn.freemadao.entity.User;
import cn.freemadao.enums.ResultEnums;
import cn.freemadao.enums.StatusCode;
import cn.freemadao.service.UserService;
import cn.freemadao.utils.CheckValue;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/findByUserName", method = RequestMethod.POST)
    public Result findByUserName(String userName) {
        if(CheckValue.checkString(userName)) {
            return new Result(StatusCode.SUCCESS, userService.findByUserName(userName));
        }
        return new Result(StatusCode.PARAMETER_ERROR, ResultEnums.PARAMETER_ERROR);
    }
    @RequestMapping("/hello")
    public String hello() {
        return "BasicFramework";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findALl() {
        return new Result(StatusCode.SUCCESS,userService.findAll());
    }
    @RequestMapping(value="findAllAmount", method = RequestMethod.GET)
    public Result findAllAmount() {
        return new Result(StatusCode.SUCCESS,userService.findAllAmount());
    }
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public Result findById(Integer id) {
        if(CheckValue.checkId(id)){
            return new Result(StatusCode.SUCCESS, userService.findById(id));
        }
        return new Result(StatusCode.PARAMETER_ERROR, ResultEnums.PARAMETER_ERROR);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(User user) {
        try{
            userService.create(user);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(Integer... ids) {
        try {
            if(CheckValue.checkIds(ids)) {
                userService.delete(ids);
                return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
            }
            return new Result(StatusCode.PARAMETER_ERROR, ResultEnums.PARAMETER_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(User user) {
        if(CheckValue.checkObj(user)) {
            try {
                userService.update(user);
                return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
            }catch (Exception e) {
                return new Result(StatusCode.ERROR, ResultEnums.ERROR);
            }
        }
        return new Result(StatusCode.PARAMETER_ERROR, ResultEnums.PARAMETER_ERROR);
    }

    @RequestMapping(value = "getUserPermission")
    public Result getUserPermission(String userName) {

        return new Result(StatusCode.SUCCESS,userService.getUserPermission(userName));
    }
}
