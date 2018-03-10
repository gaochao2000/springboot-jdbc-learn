package com.yanggaochao.springboot.learn.springbootjdbclearn.dao;

import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao.UserDO;

import java.util.List;

/**
 * 用户数据层接口
 *
 * @author 杨高超
 * @since 2018-03-09
 */
public interface UserDao {
    /**
     * 向数据库中保存一个新用户
     *
     * @param user 要保存的用户对象
     * @return 是否增肌成功
     */
    Boolean add(UserDO user);

    /**
     * 更新数据库中的一个用户
     *
     * @param user 要更新的用户对象
     * @return 是否更新成功
     */
    Boolean update(UserDO user);

    /**
     * 删除一个指定的用户
     *
     * @param id 要删除的用户的标识
     * @return 是否删除成功
     */
    boolean delete(Long id);

    /**
     * 精确查询一个指定的用户
     *
     * @param id 要查询的用户的标识
     * @return 如果能够查询到，返回用户信息，否则返回 null
     */
    UserDO locate(Long id);

    /**
     * 通过名称模糊查询用户
     *
     * @param name 要模糊查询的名称
     * @return 查询到的用户列表
     */
    List<UserDO> matchName(String name);
}
