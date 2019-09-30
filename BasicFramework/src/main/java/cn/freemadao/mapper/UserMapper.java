package cn.freemadao.mapper;

import cn.freemadao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper
public interface UserMapper {
    void create(User user);
    void delete(Integer... ids);
    void update(User user);
    User findById(Integer id);
    User findByUserName(String userName);   // extends find use username
    List<User> findAll();
    Integer findAllAmount();
    // 权限
    Set<String> getUserPermission(String userName);
}
