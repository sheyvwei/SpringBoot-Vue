package cn.freemadao.service;

import cn.freemadao.entity.Role;
import cn.freemadao.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService extends BaseService<User> {
    User findByUserName(String userName);
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    JSONObject getPermission(String username);

    List<Map<String,Object>> getUserList();

    List<Role> getAllRoles();

}
