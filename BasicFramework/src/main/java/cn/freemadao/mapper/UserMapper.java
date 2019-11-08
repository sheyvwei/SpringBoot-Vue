package cn.freemadao.mapper;

import cn.freemadao.entity.Role;
import cn.freemadao.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Mapper
public interface UserMapper {
    void create(User user);
    void delete(Integer id);
    void update(User user);
    User findById(Integer id);
    User findByUserName(String userName);   // extends find use username
    List<User> findAll();
    Integer findAllAmount();

    ////查询用户的角色 菜单 权限
    JSONObject getPermission(String userName);
    ///用户lsit
    List<Map<String, Object>> getUserList();
    //user
    List<Role> getAllRoles();
}
