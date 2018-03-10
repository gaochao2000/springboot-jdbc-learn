package com.yanggaochao.springboot.learn.springbootjdbclearn.service.impl;

import com.yanggaochao.springboot.learn.springbootjdbclearn.dao.UserDao;
import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao.UserDO;
import com.yanggaochao.springboot.learn.springbootjdbclearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户业务层实现类
 *
 * @author 杨高超
 * @since 2018-03-09
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDO add(UserDO user) {
        user.setId(new Date().getTime());
        if (userDao.add(user)) {
            return user;
        }
        return null;
    }

    @Override
    public UserDO update(UserDO user) {
        if (userDao.update(user)) {
            return locate(user.getId());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public UserDO locate(Long id) {
        return userDao.locate(id);
    }

    @Override
    public List<UserDO> matchName(String name) {
        return userDao.matchName(name);
    }
}
