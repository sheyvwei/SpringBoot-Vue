package cn.freemadao.service;

import cn.freemadao.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<User> {
    User findByUserName(String userName);
    Set<String> getUserPermission(String userName);
}
