package com.yanggaochao.springboot.learn.springbootjdbclearn.dao.impl;

import com.yanggaochao.springboot.learn.springbootjdbclearn.dao.UserDao;
import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户对象数据库访问实现类
 *
 * @author 杨高超
 * @since 2018-03-09
 */
@Repository
public class UserDaoJDBCTempletImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoJDBCTempletImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean add(UserDO user) {
        String sql = "INSERT INTO AUTH_USER(UUID,NAME) VALUES(?,?)";
        return jdbcTemplate.update(sql, user.getId(), user.getName()) > 0;
    }

    @Override
    public Boolean update(UserDO user) {
        String sql = "UPDATE AUTH_USER SET NAME = ? WHERE UUID = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId()) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM AUTH_USER WHERE UUID = ?";
        return jdbcTemplate.update(sql, id) > 0;

    }

    @Override
    public UserDO locate(Long id) {
        String sql = "SELECT * FROM AUTH_USER WHERE UUID=?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        if (rs.next()) {
            return generateEntity(rs);
        }
        return null;
    }

    @Override
    public List<UserDO> matchName(String name) {
        String sql = "SELECT * FROM AUTH_USER WHERE NAME LIKE ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
        List<UserDO> users = new ArrayList<>();
        while (rs.next()) {
            users.add(generateEntity(rs));
        }
        return users;
    }

    private UserDO generateEntity(SqlRowSet rs) {
        UserDO weChatPay = new UserDO();
        weChatPay.setId(rs.getLong("UUID"));
        weChatPay.setName(rs.getString("NAME"));
        return weChatPay;
    }
}
