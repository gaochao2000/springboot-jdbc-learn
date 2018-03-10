package com.yanggaochao.springboot.learn.springbootjdbclearn.service;

import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao.UserDO;

import java.util.List;

/**
 * 用户服务层接口
 *
 * @author 杨高超
 * @since 2018-03-09
 */
public interface UserService {

    UserDO add(UserDO user);

    UserDO update(UserDO user);

    boolean delete(Long id);

    UserDO locate(Long id);

    List<UserDO> matchName(String name);
}
