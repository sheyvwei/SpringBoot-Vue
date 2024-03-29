package cn.freemadao.service.impl;

import cn.freemadao.entity.Role;
import cn.freemadao.entity.User;
import cn.freemadao.enums.ResultEnums;
import cn.freemadao.exception.ResultException;
import cn.freemadao.mapper.UserMapper;
import cn.freemadao.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String userName){
        return userMapper.findByUserName(userName);
    }
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    public List<User> findAll() {
        return userMapper.findAll();
    }
    public Integer findAllAmount() {
        return userMapper.findAllAmount();
    }
    // crud
    @Override
    public void create(User user){
        try{
            if(user.getCreateTime() == null) {
                user.setCreateTime(new Date());
            }
            if(user.getStatus() == null) {
                user.setStatus("1");
            }
            userMapper.create(user);
        }catch (Exception e) {
            e.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }
    @Override
    public void delete(Integer... ids) {
        try{
            for(Integer id:ids){
                System.out.println(id);
                userMapper.delete(id);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }
    @Override
    public void update(User user){
        try{
            userMapper.update(user);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }
    //////  权限(菜单和角色)
    @Override
    public JSONObject getPermission(String userName) {
        JSONObject userPermission = getUserPermissionFromDB(userName);
        System.out.print(userPermission.get("permissionList"));
        return userPermission;
    }
    private JSONObject getUserPermissionFromDB(String userName) {
        JSONObject userPermission = userMapper.getPermission(userName);
        // 还需判断是否为管理员，如果是，直接给permssionList和menuList赋值
        return userPermission;
    }
    ///用户lsit
    public List<Map<String, Object>> getUserList() {
        List<Map<String, Object>> list = userMapper.getUserList();
        return list;
    }
    ///所有角色
    public List<Role> getAllRoles() {
        List<Role> roles = userMapper.getAllRoles();
        return roles;
    }
}
