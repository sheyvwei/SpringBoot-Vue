package cn.freemadao.service;

import java.util.List;

/**
 * super service
 */
public interface BaseService<T> {
    /// 增删改查
    void create(T t);
    void delete(Integer... ids);    //删除多个
    void update(T t);
    T findById(Integer id);
    List<T> findAll();
    Integer findAllAmount();
}
